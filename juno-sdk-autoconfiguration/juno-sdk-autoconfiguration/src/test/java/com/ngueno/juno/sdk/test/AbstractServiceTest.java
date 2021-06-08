package com.ngueno.juno.sdk.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockserver.integration.ClientAndServer;
import org.mockserver.junit.jupiter.MockServerExtension;
import org.mockserver.junit.jupiter.MockServerSettings;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import com.ngueno.juno.sdk.test.mockserver.MockServerManager;

@SpringBootTest(webEnvironment = WebEnvironment.NONE)
@ExtendWith(MockServerExtension.class)
@MockServerSettings(ports = 1080, perTestSuite = true)
public abstract class AbstractServiceTest extends AbstractTest {

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
