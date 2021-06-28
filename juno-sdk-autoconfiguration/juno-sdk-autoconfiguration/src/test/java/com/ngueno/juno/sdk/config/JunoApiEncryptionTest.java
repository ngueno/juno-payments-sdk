package com.ngueno.juno.sdk.config;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.ngueno.juno.sdk.test.AbstractTest;

import org.junit.jupiter.api.Test;

class JunoApiEncryptionTest extends AbstractTest {

    @Test
    void constants() {
        assertEquals("RSA", JunoApiEncryption.CRYPTO_METHOD);
        assertEquals("SHA-256", JunoApiEncryption.MD_NAME);
        assertEquals("MGF1", JunoApiEncryption.MG_NAME);
        assertEquals("RSA/ECB/OAEPPadding", JunoApiEncryption.CRYPTO_TRANSFORM);
    }
}
