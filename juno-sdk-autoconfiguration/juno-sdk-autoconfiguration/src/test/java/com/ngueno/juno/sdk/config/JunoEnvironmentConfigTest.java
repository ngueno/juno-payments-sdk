package com.ngueno.juno.sdk.config;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.Duration;

import javax.annotation.Resource;

import com.ngueno.juno.sdk.model.environment.JunoEnvironment;
import com.ngueno.juno.sdk.test.AbstractSpringBootTest;

import org.junit.jupiter.api.Test;

class JunoEnvironmentConfigTest extends AbstractSpringBootTest {

    @Test
    void junoEnvironment() {
        JunoEnvironment junoEnvironment = config.junoEnvironment();
        assertEquals("http://localhost:1080", junoEnvironment.getApiv2Endpoint());
        assertEquals(3, junoEnvironment.getApiv2Timeout());
        assertEquals(Duration.ofSeconds(3), junoEnvironment.getApiv2TimeoutDuration());

        assertEquals("http://localhost:1080/auth-server", junoEnvironment.getAuthServerEndpoint());
        assertEquals(2, junoEnvironment.getAuthServerTimeout());
        assertEquals(Duration.ofSeconds(2), junoEnvironment.getAuthServerTimeoutDuration());
    }

    @Resource
    private JunoEnvironmentConfig config;
}
