package com.ngueno.juno.sdk.resources.handler;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;

import java.util.List;

import com.ngueno.juno.sdk.test.AbstractWebhookTest;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

class JunoEventHandlerControllerTest extends AbstractWebhookTest {

    @Test
    void annotations() {
        assertTrue(JunoEventHandlerController.class.isAnnotationPresent(RestController.class));

        assertTrue(JunoEventHandlerController.class.isAnnotationPresent(RequestMapping.class));
        assertThat(List.of(JunoEventHandlerController.class.getAnnotation(RequestMapping.class).value()),
                hasItems("${juno.webhooks.endpoint.request-mapping:/juno/events}"));

        assertTrue(JunoEventHandlerController.class.isAnnotationPresent(ConditionalOnProperty.class));
        ConditionalOnProperty conditionalOnProperty = JunoEventHandlerController.class.getAnnotation(ConditionalOnProperty.class);
        assertThat(List.of(conditionalOnProperty.name()), hasItems("juno.webhooks.endpoint.enabled"));
        assertEquals("true", conditionalOnProperty.havingValue());
    }

    @Test
    void handleEvent() {
        controller.handleEvent("Some random event");
        verify(handler).handle("Some random event");
    }

    @Mock
    private JunoEventHandlerService handler;

    @InjectMocks
    private JunoEventHandlerController controller;
}
