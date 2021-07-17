package com.ngueno.juno.sdk.resources.credentials.model;

import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

import com.ngueno.juno.sdk.model.error.JunoApiIntegrationException;

import org.apache.commons.lang3.StringUtils;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@RequiredArgsConstructor
public final class JunoPublicKey {

    private static final String BEGIN_PUBLIC_KEY = "-----BEGIN PUBLIC KEY-----";
    private static final String END_PUBLIC_KEY = "-----END PUBLIC KEY-----";

    private final String key;

    public PublicKey getPublicKey() {
        try {
            String publicKey = key.replace(BEGIN_PUBLIC_KEY, StringUtils.EMPTY).replace(END_PUBLIC_KEY, StringUtils.EMPTY);

            byte[] encodedPublicKey = Base64.getMimeDecoder().decode(publicKey.trim());
            X509EncodedKeySpec spec = new X509EncodedKeySpec(encodedPublicKey);
            KeyFactory kf = KeyFactory.getInstance("RSA");

            return kf.generatePublic(spec);
        } catch (Exception e) {
            throw new JunoApiIntegrationException("Error retrieving public key", e);
        }
    }
}