package com.ngueno.juno.sdk.resources.validation;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JunoEventValidationService {

    public void validateEvent(Map<String, String[]> requestParams, String eventPayload, String eventSignature) {
        String webhookSecret = webhookService.findWebhookSecret(requestParams);
        signatureValidator.validate(webhookSecret, eventPayload, eventSignature);
    }

    @Autowired
    private WebhookService webhookService;

    @Autowired
    private JunoEventSignatureValidator signatureValidator;

}
