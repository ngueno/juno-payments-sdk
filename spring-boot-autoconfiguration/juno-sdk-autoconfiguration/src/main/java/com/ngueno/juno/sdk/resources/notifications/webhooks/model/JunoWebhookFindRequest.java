package com.ngueno.juno.sdk.resources.notifications.webhooks.model;

import com.ngueno.juno.sdk.resources.base.http.JunoBaseRequest;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class JunoWebhookFindRequest implements JunoBaseRequest {

    private final String webhookId;
}
