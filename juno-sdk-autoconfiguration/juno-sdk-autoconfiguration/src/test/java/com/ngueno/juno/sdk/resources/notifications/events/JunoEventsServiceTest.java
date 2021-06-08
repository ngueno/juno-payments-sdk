package com.ngueno.juno.sdk.resources.notifications.events;

import javax.annotation.Resource;

import org.junit.jupiter.api.Test;

import com.ngueno.juno.sdk.resources.notifications.model.EventTypeResources;
import com.ngueno.juno.sdk.test.AbstractServiceTest;

class JunoEventsServiceTest extends AbstractServiceTest {

    @Test
    void listEventTypes() {
        mockServerManager().expectListEventTypes();

        EventTypeResources resources = service.listEventTypes();

    }

    @Resource
    private JunoEventsService service;
}
