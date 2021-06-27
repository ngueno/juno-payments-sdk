package com.ngueno.juno.sdk.resources.notifications.webhooks;

import static com.ngueno.juno.sdk.resources.base.model.EventTypeStatus.ENABLED;
import static com.ngueno.juno.sdk.resources.base.model.WebhookStatus.ACTIVE;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import javax.annotation.Resource;

import com.ngueno.juno.sdk.resources.notifications.AbstractNotificationsServiceTest;
import com.ngueno.juno.sdk.resources.notifications.model.WebhookResource;
import com.ngueno.juno.sdk.resources.notifications.model.WebhookResources;
import com.ngueno.juno.sdk.resources.notifications.webhooks.model.JunoWebhookCreateRequest;
import com.ngueno.juno.sdk.resources.notifications.webhooks.model.JunoWebhookFindRequest;
import com.ngueno.juno.sdk.resources.notifications.webhooks.model.JunoWebhookRemoveRequest;
import com.ngueno.juno.sdk.resources.notifications.webhooks.model.JunoWebhookUpdateRequest;

import org.junit.jupiter.api.Test;

class JunoWebhookServiceTest extends AbstractNotificationsServiceTest {

    @Test
    void createWebhook() {
        JunoWebhookCreateRequest request = new JunoWebhookCreateRequest("https://webhook.site/2a2b472a-5889-4a8c-8562-ebb0f28928df",
                List.of("PAYMENT_NOTIFICATION"));

        mockServerManager().expectWebhookCreate();

        WebhookResource resource = service.createWebhook(request);

        assertWebhook(resource);
    }

    @Test
    void updateWebhook() {
        JunoWebhookUpdateRequest request = new JunoWebhookUpdateRequest("wbh_8E345951C46BBEED", List.of("PAYMENT_NOTIFICATION"));

        mockServerManager().expectWebhookUpdate(request);

        WebhookResource resource = service.updateWebhook(request);

        assertWebhook(resource);
    }

    @Test
    void listWebhook() {
        mockServerManager().expectWebhookList();

        WebhookResources resources = service.listWebhooks();

        assertEquals(1, resources.getResources().size());
        assertWebhook(resources.getResources().get(0));
    }

    @Test
    void findWebhook() {
        JunoWebhookFindRequest request = new JunoWebhookFindRequest("wbh_8E345951C46BBEED");

        mockServerManager().expectWebhookFind(request);

        WebhookResource resource = service.findWebhook(request);
        assertWebhook(resource);
    }

    @Test
    void removeWebhook() {
        JunoWebhookRemoveRequest request = new JunoWebhookRemoveRequest("wbh_8E345951C46BBEED");

        mockServerManager().expectWebhookRemove(request);

        service.removeWebhook(request);
    }

    private void assertWebhook(WebhookResource resource) {
        assertEquals("wbh_8E345951C46BBEED", resource.getId());
        assertEquals("https://webhook.site/2a2b472a-5889-4a8c-8562-ebb0f28928df", resource.getUrl());
        assertEquals("3c4c27504dea9bff7e95a5db8f449c767bce72ab86e0d514e3f7f3e09f44b9fe", resource.getSecret());
        assertEquals(ACTIVE, resource.getStatus());

        assertEquals(1, resource.getEventTypes().size());
        assertEventType(resource.getEventTypes().get(0), "evt_3891C9EEE7F6CC9A", "PAYMENT_NOTIFICATION", "Uma notificação de pagamento foi gerada",
                ENABLED);
    }

    @Resource
    private JunoWebhookService service;
}
