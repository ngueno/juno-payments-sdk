package com.ngueno.juno.sdk.resources.handler;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.fasterxml.jackson.databind.node.TextNode;
import com.ngueno.juno.sdk.test.AbstractWebhookTest;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

class DefaultJunoEventHandlerTest extends AbstractWebhookTest {

    @Test
    void getEventType() {
        assertEquals("default", handler.getEventType());
    }

    @Test
    void handleEvent() {
        assertDoesNotThrow(() -> handler.handleEvent(new JunoEventWrapper(new TextNode("Some example text"))));
    }

    @InjectMocks
    private DefaultJunoEventHandler handler;
}
