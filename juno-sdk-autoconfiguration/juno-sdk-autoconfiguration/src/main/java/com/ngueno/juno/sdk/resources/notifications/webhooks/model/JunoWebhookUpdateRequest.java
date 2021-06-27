package com.ngueno.juno.sdk.resources.notifications.webhooks.model;

import java.util.List;

import com.ngueno.juno.sdk.resources.base.http.JunoBaseRequest;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class JunoWebhookUpdateRequest implements JunoBaseRequest {

    private final String webhookId;
    private final List<String> eventTypes;
}
