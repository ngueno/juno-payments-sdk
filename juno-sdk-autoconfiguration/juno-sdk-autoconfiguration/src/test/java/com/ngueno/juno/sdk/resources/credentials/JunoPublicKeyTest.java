package com.ngueno.juno.sdk.resources.credentials;

import static com.ngueno.juno.sdk.test.FixtureHelper.getResource;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.ngueno.juno.sdk.model.error.JunoApiIntegrationException;
import com.ngueno.juno.sdk.test.AbstractSpringBootTest;

import org.junit.jupiter.api.Test;

class JunoPublicKeyTest extends AbstractSpringBootTest {

    @Test
    void getPublicKey() {
        String publicKey = getMockKey();
        JunoPublicKey junoPublicKey = new JunoPublicKey(publicKey);

        assertEquals(publicKey, junoPublicKey.getKey());
        assertEquals("X.509", junoPublicKey.getPublicKey().getFormat());
        assertEquals("RSA", junoPublicKey.getPublicKey().getAlgorithm());
    }

    @Test
    void getPublicKeyInvalid() {
        JunoPublicKey junoPublicKey = new JunoPublicKey("anyStuff");
        assertThrows(JunoApiIntegrationException.class, () -> junoPublicKey.getPublicKey(), "Error retrieving public key");
    }

    @Test
    void toStringTest() {
        assertEquals("JunoPublicKey(key=-----BEGIN PUBLIC KEY-----\r\n" //
                + "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA4AaeEEJCs+IzRsNZkrBiJYcGanhE1eZl\r\n" //
                + "4LM5kMcRLuVBwxh5SLrUUVMdGZ8xvHTs8Lz5qtwfSBBvtG9EXQo+cPGa6zGF48FQIe4eWu9DKY5F\r\n" //
                + "6yR2dBOc7ssAdzCaeW4cTz7aLsr6pLfHqkguFSFIW+4t61Yipb3G/HmEGBk1B8c5PWA8ajRFwLmT\r\n" //
                + "kMxAgD7wQU/vqnYEJOvOZgxyhsuEOEDD+CfCRqRjk0YcUUyDmWNVH2vlYFYgmEZO+sFLnk/YVQu9\r\n" //
                + "Q+Dh/WuvZJtGu9iax50a0E0DfddUO8Ay/8FPQI1xJB40ZKpGw/BAL6aqu6klP5ij7mIBwI8SnlMZ\r\n" //
                + "NQnX+QIDAQAB\r\n" //
                + "-----END PUBLIC KEY-----)", new JunoPublicKey(getMockKey()).toString());
    }

    private String getMockKey() {
        return getResource("mockserver", "credentials", "publickey", "GET.mock");
    }
}
