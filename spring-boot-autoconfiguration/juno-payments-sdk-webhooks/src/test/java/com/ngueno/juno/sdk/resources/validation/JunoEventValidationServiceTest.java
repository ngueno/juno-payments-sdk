package com.ngueno.juno.sdk.resources.validation;

import static com.ngueno.juno.sdk.test.FixtureHelper.EVENT_SIGNATURE;
import static com.ngueno.juno.sdk.test.FixtureHelper.WEBHOOK_SECRET;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Map;

import com.ngueno.juno.sdk.test.AbstractWebhookTest;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

class JunoEventValidationServiceTest extends AbstractWebhookTest {

    @Test
    void validateEvent() {
        Map<String, String[]> requestParams = Map.of();
        String payload = helper().getEventPayload();

        when(webhookService.findWebhookSecret(requestParams)).thenReturn(WEBHOOK_SECRET);

        service.validateEvent(requestParams, payload, EVENT_SIGNATURE);

        verify(webhookService).findWebhookSecret(requestParams);
        verify(validator).validate(WEBHOOK_SECRET, payload, EVENT_SIGNATURE);
    }

    @Mock
    private WebhookService webhookService;

    @Mock
    private JunoEventSignatureValidator validator;

    @InjectMocks
    private JunoEventValidationService service;
}
