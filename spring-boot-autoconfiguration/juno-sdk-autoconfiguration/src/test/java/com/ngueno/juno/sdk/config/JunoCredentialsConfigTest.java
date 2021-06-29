package com.ngueno.juno.sdk.config;

import static org.junit.jupiter.api.Assertions.assertEquals;

import javax.annotation.Resource;

import com.ngueno.juno.sdk.model.security.JunoApiCredentials;
import com.ngueno.juno.sdk.test.AbstractSpringBootTest;

import org.junit.jupiter.api.Test;

class JunoCredentialsConfigTest extends AbstractSpringBootTest {

    @Test
    void apiCredentials() {
        JunoApiCredentials apiCredentials = config.apiCredentials();
        assertEquals("ZHVtbXlDbGllbnRJZDpkdW1teUNsaWVudFNlY3JldA==", apiCredentials.getAuthorization());
        assertEquals("dummyPublicToken", apiCredentials.getPublicToken());
        assertEquals("dummyResourceToken", apiCredentials.getResourceToken());
    }

    @Resource
    private JunoCredentialsConfig config;
}
