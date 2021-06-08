package com.ngueno.juno.sdk.resources.notifications.events;

import static com.ngueno.juno.sdk.resources.base.model.EventTypeStatus.ENABLED;
import static org.junit.jupiter.api.Assertions.assertEquals;

import javax.annotation.Resource;

import com.ngueno.juno.sdk.resources.notifications.AbstractNotificationsServiceTest;
import com.ngueno.juno.sdk.resources.notifications.model.EventTypeResources;

import org.junit.jupiter.api.Test;

class JunoEventsServiceTest extends AbstractNotificationsServiceTest {

    @Test
    void listEventTypes() {
        mockServerManager().expectListEventTypes();

        EventTypeResources resources = service.listEventTypes();

        assertEquals(2, resources.getResources().size());
        assertEventType(resources.getResources().get(0), "evt_DC2E7E8848B08C62", "DOCUMENT_STATUS_CHANGED", "O status de um documento foi alterado",
                ENABLED);
        assertEventType(resources.getResources().get(1), "evt_4B3A979C94349E9E", "DIGITAL_ACCOUNT_STATUS_CHANGED",
                "O estado de uma conta digital foi alterado", ENABLED);
    }

    @Resource
    private JunoEventsService service;
}
