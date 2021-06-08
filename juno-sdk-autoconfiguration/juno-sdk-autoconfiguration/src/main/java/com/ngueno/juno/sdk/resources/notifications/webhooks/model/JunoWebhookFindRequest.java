package com.ngueno.juno.sdk.resources.notifications.webhooks.model;

import com.ngueno.juno.sdk.resources.base.http.JunoBaseRequest;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@RequiredArgsConstructor
public class JunoWebhookFindRequest implements JunoBaseRequest {

    private final String webhookId;
}
