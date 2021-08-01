package com.ngueno.juno.sdk.resources.validation;

import static java.nio.charset.StandardCharsets.UTF_8;

import com.google.common.hash.Hashing;
import com.ngueno.juno.sdk.model.error.JunoApiIntegrationException;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class JunoEventSignatureValidator {

    public void validate(String webhookSecret, String eventPayload, String eventSignature) {
        validateFields(webhookSecret, eventPayload, eventSignature);
        assertValidEventSignature(calculateSignature(webhookSecret, eventPayload), eventSignature);
    }

    private void assertValidEventSignature(String calculatedSignature, String eventSignature) {
        log.trace("[JUNO-SDK] Checking signature match");
        log.trace("[JUNO-SDK] Calculated  {}", calculatedSignature);
        log.trace("[JUNO-SDK] X-Signature {}", eventSignature);

        if (!calculatedSignature.equals(eventSignature)) {
            throw new JunoApiIntegrationException("Calculated event signature does not match expected signature.");
        }
    }

    private String calculateSignature(String webhookSecret, String eventPayload) {
        return Hashing //
                .hmacSha256(webhookSecret.getBytes(UTF_8)) //
                .hashString(eventPayload, UTF_8) //
                .toString();
    }

    private void validateFields(String webhookSecret, String eventPayload, String eventSignature) {
        if (StringUtils.isBlank(webhookSecret)) {
            throw new JunoApiIntegrationException("Webhook secret cannot be null or empty");
        }

        if (StringUtils.isBlank(eventPayload)) {
            throw new JunoApiIntegrationException("Event payload cannot be null or empty");
        }

        if (StringUtils.isBlank(eventSignature)) {
            throw new JunoApiIntegrationException("Event signature cannot be null or empty");
        }
    }
}
