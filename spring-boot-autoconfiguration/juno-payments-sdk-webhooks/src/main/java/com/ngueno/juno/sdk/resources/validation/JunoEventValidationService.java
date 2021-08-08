package com.ngueno.juno.sdk.resources.validation;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

@Service
public class JunoEventValidationService {

    public void validateEvent(Map<String, String[]> requestParams, String eventPayload, String eventSignature) {
        signatureValidator.validate(webhookService.findWebhookSecret(requestParams), eventPayload, eventSignature);
    }

    @Resource
    private WebhookService webhookService;

    @Resource
    private JunoEventSignatureValidator signatureValidator;

}
