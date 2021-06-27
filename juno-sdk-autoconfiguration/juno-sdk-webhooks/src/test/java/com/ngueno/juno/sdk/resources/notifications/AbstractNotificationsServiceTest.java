package com.ngueno.juno.sdk.resources.notifications;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.ngueno.juno.sdk.resources.base.model.EventTypeStatus;
import com.ngueno.juno.sdk.resources.notifications.model.EventTypeResource;
import com.ngueno.juno.sdk.test.AbstractServiceTest;

public abstract class AbstractNotificationsServiceTest extends AbstractServiceTest {

    protected void assertEventType(EventTypeResource resource, String id, String name, String label, EventTypeStatus status) {
        assertEquals(id, resource.getId());
        assertEquals(name, resource.getName());
        assertEquals(label, resource.getLabel());
        assertEquals(status, resource.getStatus());
    }
}
