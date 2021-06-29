package com.ngueno.juno.sdk.utils;

import static com.ngueno.juno.sdk.config.JunoApiEncryption.CRYPTO_TRANSFORM;
import static com.ngueno.juno.sdk.config.JunoApiEncryption.MD_NAME;
import static com.ngueno.juno.sdk.config.JunoApiEncryption.MG_NAME;
import static java.security.spec.MGF1ParameterSpec.SHA256;
import static javax.crypto.Cipher.ENCRYPT_MODE;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.GZIPOutputStream;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.OAEPParameterSpec;
import javax.crypto.spec.PSource;

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

    public static String encryptCreditCard(PublicKey publicKey, byte[] creditCardData) {
        try {
            OAEPParameterSpec parameterSpec = new OAEPParameterSpec(MD_NAME, MG_NAME, SHA256, PSource.PSpecified.DEFAULT);

            Cipher cipher = Cipher.getInstance(CRYPTO_TRANSFORM);
            cipher.init(ENCRYPT_MODE, publicKey, parameterSpec);

            byte[] encryptedBytes = cipher.doFinal(creditCardData);

            return Base64.getEncoder().encodeToString(encryptedBytes);
        } catch (InvalidKeyException | InvalidAlgorithmParameterException | IllegalBlockSizeException | BadPaddingException | NoSuchAlgorithmException
                | NoSuchPaddingException e) {
            throw new JunoApiIntegrationException("Error encrypting credit card data", e);
        }
    }

    public static byte[] encryptFile(PublicKey publicKey, String fileName, InputStream file) {
        String fileContent = encodeBase64File(file);

        // Json specification
        String content = generateJsonSpecification(fileName, fileContent);

        // JWT token generation
        String jweToken = generateJweToken(publicKey, content);

        // JWT token compression
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        compressJwe(output, jweToken);
        return output.toByteArray();
    }

    private static String generateJsonSpecification(String fileName, String fileContent) {
        Map<String, Object> map = new HashMap<>();
        map.put("fn", fileName);
        map.put("fc", fileContent);
        return JsonUtil.toJson(map);
    }

    private static String encodeBase64File(InputStream file) {
        return Base64.getEncoder().encodeToString(readFile(file));
    }

    private static byte[] readFile(InputStream file) {
        try {
            return IOUtils.toByteArray(file);
        } catch (IOException e) {
            throw new JunoApiIntegrationException("Error reading file contents", e);
        }
    }

    private static String generateJweToken(PublicKey publicKey, String content) {
        try {
            JsonWebEncryption jwe = new JsonWebEncryption();
            jwe.setAlgorithmHeaderValue(KeyManagementAlgorithmIdentifiers.RSA_OAEP_256);
            jwe.setEncryptionMethodHeaderParameter(ContentEncryptionAlgorithmIdentifiers.AES_256_GCM);
            jwe.enableDefaultCompression();
            jwe.setKey(publicKey);
            jwe.setPlaintext(content);
            return jwe.getCompactSerialization();
        } catch (JoseException e) {
            throw new JunoApiIntegrationException("Error generating JWE token", e);
        }
    }

    private static void compressJwe(OutputStream output, String jweToken) {
        try {
            GZIPOutputStream gzip = new GZIPOutputStream(output);
            gzip.write(jweToken.getBytes(StandardCharsets.UTF_8));
            gzip.close();
        } catch (IOException e) {
            throw new JunoApiIntegrationException("Error compressing JWE", e);
        }
    }
}