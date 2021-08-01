package com.ngueno.juno.sdk.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.security.PublicKey;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.GZIPOutputStream;

import com.ngueno.juno.sdk.model.error.JunoApiIntegrationException;

import org.apache.commons.io.IOUtils;
import org.jose4j.json.JsonUtil;
import org.jose4j.jwe.ContentEncryptionAlgorithmIdentifiers;
import org.jose4j.jwe.JsonWebEncryption;
import org.jose4j.jwe.KeyManagementAlgorithmIdentifiers;
import org.jose4j.lang.JoseException;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class JweUtils {

    public static byte[] encryptFile(PublicKey publicKey, String fileName, InputStream file) {
        try {
            String fileContent = encodeBase64File(file);

            // Json specification
            String content = generateJsonSpecification(fileName, fileContent);

            // JWT token generation
            String jweToken = generateJweToken(publicKey, content);

            // JWT token compression
            return compressJwe(jweToken);
        } catch (IOException | JoseException e) {
            throw new JunoApiIntegrationException("Failed to generate encrypted file", e);
        }
    }

    private static String generateJsonSpecification(String fileName, String fileContent) {
        Map<String, Object> map = new HashMap<>();
        map.put("fn", fileName);
        map.put("fc", fileContent);
        return JsonUtil.toJson(map);
    }

    private static String encodeBase64File(InputStream file) throws IOException {
        return Base64.getEncoder().encodeToString(IOUtils.toByteArray(file));
    }

    private static String generateJweToken(PublicKey publicKey, String content) throws JoseException {
        JsonWebEncryption jwe = new JsonWebEncryption();
        jwe.setAlgorithmHeaderValue(KeyManagementAlgorithmIdentifiers.RSA_OAEP_256);
        jwe.setEncryptionMethodHeaderParameter(ContentEncryptionAlgorithmIdentifiers.AES_256_GCM);
        jwe.enableDefaultCompression();
        jwe.setKey(publicKey);
        jwe.setPlaintext(content);
        return jwe.getCompactSerialization();
    }

    private static byte[] compressJwe(String jweToken) throws IOException {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        compressJwe(output, jweToken);
        return output.toByteArray();
    }

    private static void compressJwe(OutputStream output, String jweToken) throws IOException {
        GZIPOutputStream gzip = new GZIPOutputStream(output);
        gzip.write(jweToken.getBytes(StandardCharsets.UTF_8));
        gzip.close();
    }
}