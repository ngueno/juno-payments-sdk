package com.ngueno.juno.sdk.resources.validation;

import static com.ngueno.juno.sdk.test.FixtureHelper.EVENT_SIGNATURE;
import static com.ngueno.juno.sdk.test.FixtureHelper.WEBHOOK_SECRET;
import static org.apache.commons.lang3.StringUtils.EMPTY;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.ngueno.juno.sdk.model.error.JunoApiIntegrationException;
import com.ngueno.juno.sdk.test.AbstractWebhookTest;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

class JunoEventSignatureValidatorTest extends AbstractWebhookTest {

    @Test
    void validate() {
        validator.validate(WEBHOOK_SECRET, helper().getEventPayload(), EVENT_SIGNATURE);
    }

    @Test
    void validateInvalidSignature() {
        assertThrows(JunoApiIntegrationException.class,
                () -> validator.validate(EVENT_SIGNATURE, WEBHOOK_SECRET, "e3b0c44298fc1c149afbf4c8996fb92427ae41e4649b934ca495991b7852b855"),
                "Calculated event signature does not match expected signature.");
    }

    @Test
    void validateFields() {
        assertThrows(JunoApiIntegrationException.class, () -> validator.validate(null, helper().getEventPayload(), EVENT_SIGNATURE));
        assertThrows(JunoApiIntegrationException.class, () -> validator.validate(WEBHOOK_SECRET, null, EVENT_SIGNATURE));
        assertThrows(JunoApiIntegrationException.class, () -> validator.validate(WEBHOOK_SECRET, helper().getEventPayload(), null));

        assertThrows(JunoApiIntegrationException.class, () -> validator.validate(EMPTY, helper().getEventPayload(), EVENT_SIGNATURE));
        assertThrows(JunoApiIntegrationException.class, () -> validator.validate(WEBHOOK_SECRET, EMPTY, EVENT_SIGNATURE));
        assertThrows(JunoApiIntegrationException.class, () -> validator.validate(WEBHOOK_SECRET, helper().getEventPayload(), EMPTY));
    }

    @InjectMocks
    private JunoEventSignatureValidator validator;
}
