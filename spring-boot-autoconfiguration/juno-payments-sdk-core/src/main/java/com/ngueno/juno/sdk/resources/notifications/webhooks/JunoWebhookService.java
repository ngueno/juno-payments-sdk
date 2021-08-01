package com.ngueno.juno.sdk.resources.notifications.webhooks;

import com.ngueno.juno.sdk.resources.base.http.JunoBaseService;
import com.ngueno.juno.sdk.resources.notifications.model.WebhookResource;
import com.ngueno.juno.sdk.resources.notifications.model.WebhookResources;
import com.ngueno.juno.sdk.resources.notifications.webhooks.model.JunoWebhookCreateRequest;
import com.ngueno.juno.sdk.resources.notifications.webhooks.model.JunoWebhookFindRequest;
import com.ngueno.juno.sdk.resources.notifications.webhooks.model.JunoWebhookRemoveRequest;
import com.ngueno.juno.sdk.resources.notifications.webhooks.model.JunoWebhookUpdateRequest;

import org.springframework.stereotype.Component;

@Component
public class JunoWebhookService extends JunoBaseService {

    private static final String WEBHOOKS = "/notifications/webhooks";
    private static final String WEBHOOKS_ID = WEBHOOKS + "/{id}";

    public WebhookResource createWebhook(JunoWebhookCreateRequest request) {
        return createWebhook(getResourceToken(), request);
    }

    public WebhookResource createWebhook(String resourceToken, JunoWebhookCreateRequest request) {
        return http().post(WEBHOOKS, resourceToken, request, WebhookResource.class);
    }

    public WebhookResources listWebhooks() {
        return listWebhooks(getResourceToken());
    }

    public WebhookResources listWebhooks(String resourceToken) {
        return http().get(WEBHOOKS, resourceToken, WebhookResources.class);
    }

    public WebhookResource findWebhook(JunoWebhookFindRequest request) {
        return findWebhook(getResourceToken(), request);
    }

    public WebhookResource findWebhook(String resourceToken, JunoWebhookFindRequest request) {
        return http().get(expandId(WEBHOOKS_ID, request.getWebhookId()), resourceToken, WebhookResource.class);
    }

    public WebhookResource updateWebhook(JunoWebhookUpdateRequest request) {
        return updateWebhook(getResourceToken(), request);
    }

    public WebhookResource updateWebhook(String resourceToken, JunoWebhookUpdateRequest request) {
        return http().patch(expandId(WEBHOOKS_ID, request.getWebhookId()), resourceToken, request,
                WebhookResource.class);
    }

    public void removeWebhook(JunoWebhookRemoveRequest request) {
        removeWebhook(getResourceToken(), request);
    }

    public void removeWebhook(String resourceToken, JunoWebhookRemoveRequest request) {
        http().delete(expandId(WEBHOOKS_ID, request.getWebhookId()), resourceToken, Void.class);
    }
}
