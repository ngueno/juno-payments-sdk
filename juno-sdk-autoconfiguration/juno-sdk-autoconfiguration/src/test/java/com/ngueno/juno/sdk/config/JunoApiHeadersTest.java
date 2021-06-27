package com.ngueno.juno.sdk.config;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.ngueno.juno.sdk.test.AbstractTest;

import org.junit.jupiter.api.Test;

class JunoApiHeadersTest extends AbstractTest {

    @Test
    void constants() {
        assertEquals("X-Api-Version", JunoApiHeaders.X_API_VERSION);
        assertEquals("X-Resource-Token", JunoApiHeaders.X_RESOURCE_TOKEN);
        assertEquals("Authorization", JunoApiHeaders.AUTHORIZATION);
        assertEquals("Content-Type", JunoApiHeaders.CONTENT_TYPE);
        assertEquals("grant_type", JunoApiHeaders.GRANT_TYPE);
        assertEquals("client_credentials", JunoApiHeaders.CLIENT_CREDENTIALS);
        assertEquals("Basic %s", JunoApiHeaders.BASIC_AUTHENTICATION);
        assertEquals("Bearer %s", JunoApiHeaders.BEARER_AUTHENTICATION);
        assertEquals("2", JunoApiHeaders.API_VERSION);
    }
}
