package com.ngueno.juno.sdk.resources.validation;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@ConditionalOnProperty(name = "juno.webhooks.main-webhook-service.enabled", havingValue = "true")
public class MainWebhookService implements WebhookService {

    @Override
    public String findWebhookSecret(Map<String, String[]> requestParams) {
        log.debug("[JUNO-SDK] Returning juno.webhooks-main.webhook-service.secret: {}", mainWebhookSecret);
        return mainWebhookSecret;
    }

    @Value("${juno.webhooks-main.webhook-service.secret}")
    private String mainWebhookSecret;
}
