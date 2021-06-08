package com.ngueno.juno.sdk.resources.credentials;

import static com.ngueno.juno.sdk.test.FixtureHelper.getResource;
import static org.junit.jupiter.api.Assertions.assertEquals;

import javax.annotation.Resource;

import org.junit.jupiter.api.Test;

import com.ngueno.juno.sdk.test.AbstractServiceTest;

class JunoCredentialsServiceTest extends AbstractServiceTest {

    @Test
    void getPublicKey() {
        mockServerManager().expectCredentialsPublicKeyRequest();

        JunoPublicKey publicKey = service.getPublicKey();

        assertEquals(getResource("mockserver", "credentials", "publickey", "GET.mock"), publicKey.getKey());
        assertEquals("X.509", publicKey.getPublicKey().getFormat());
        assertEquals("RSA", publicKey.getPublicKey().getAlgorithm());
    }

    @Resource
    private JunoCredentialsService service;
}
