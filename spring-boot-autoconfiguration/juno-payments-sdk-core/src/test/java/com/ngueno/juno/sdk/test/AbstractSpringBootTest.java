package com.ngueno.juno.sdk.test;

import com.ngueno.juno.sdk.config.JunoSdkConfig;
import com.ngueno.juno.sdk.test.mockserver.MockServerManager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockserver.integration.ClientAndServer;
import org.mockserver.junit.jupiter.MockServerExtension;
import org.mockserver.junit.jupiter.MockServerSettings;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

@ExtendWith(MockServerExtension.class)
@MockServerSettings(ports = 1080, perTestSuite = true)
@SpringBootTest(webEnvironment = WebEnvironment.NONE, classes = JunoSdkConfig.class)
public abstract class AbstractSpringBootTest extends AbstractTest {

    @BeforeEach
    public void initializeMockServer(ClientAndServer mockServer) {
        this.mockServerManager = new MockServerManager(mockServer);
        mockServerManager.configureDefaultExpectations();
    }

    protected MockServerManager mockServerManager() {
        return mockServerManager;
    }

    private MockServerManager mockServerManager;
}
