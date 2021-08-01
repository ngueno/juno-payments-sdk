package com.ngueno.juno.sdk.resources.validation;

import java.util.Map;

public interface WebhookService {

    String findWebhookSecret(Map<String, String[]> requestParams);
}
