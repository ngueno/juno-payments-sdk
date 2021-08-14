package com.ngueno.juno.sdk.resources.handler;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.util.ReflectionTestUtils.setField;

import java.util.Set;

import com.ngueno.juno.sdk.model.error.JunoApiIntegrationException;
import com.ngueno.juno.sdk.test.AbstractWebhookTest;
import com.ngueno.juno.sdk.test.BaseFixtureHelper;
import com.ngueno.juno.sdk.utils.JacksonUtils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;

class JunoEventHandlerServiceTest extends AbstractWebhookTest {

    @BeforeEach
    void configure() {
        configureEventHandlers(defaultHandler);
        setField(service, "junoObjectMapper", JacksonUtils.objectMapper());
    }

    @Test
    void handleWithProperEventHandler() {
        configureEventHandlers(defaultHandler, paymentNotificationHandler);
        internalHandle(paymentNotificationHandler);
    }

    @Test
    void handleWithDefaultHandler() {
        internalHandle(defaultHandler);
    }

    @Test
    void handleWithInvalidJson() {
        assertThrows(JunoApiIntegrationException.class, () -> service.handle("Invalid json"),
                "[JUNO-SDK] Unable to convert received event to JsonNode");

    }

    private void internalHandle(JunoEventHandler expectedHandler) {
        ArgumentCaptor<JunoEventWrapper> captor = ArgumentCaptor.forClass(JunoEventWrapper.class);

        String event = getEvent();
        service.handle(event);

        verify(expectedHandler).handleEvent(captor.capture());
        assertEquals(event, captor.getValue().toString());

    }

    private void configureEventHandlers(JunoEventHandler... handlers) {
        Set<JunoEventHandler> setOfHandlers = Set.of(handlers);

        setOfHandlers.forEach(handler -> {
            when(handler.getEventType()).thenCallRealMethod();
        });

        setField(service, "eventHandlers", setOfHandlers);
        service.configure();
    }

    private String getEvent() {
        return BaseFixtureHelper.getResource("events", "payment_notification.json");
    }

    private static class PaymentNotificationHandler implements JunoEventHandler {

        @Override
        public String getEventType() {
            return "PAYMENT_NOTIFICATION";
        }

        @Override
        public void handleEvent(JunoEventWrapper event) {
            // NTD
        }
    }

    @Mock
    private PaymentNotificationHandler paymentNotificationHandler;

    @Mock
    private DefaultJunoEventHandler defaultHandler;

    @InjectMocks
    private JunoEventHandlerService service;
}
