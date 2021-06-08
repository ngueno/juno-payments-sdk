package com.ngueno.juno.sdk.resources.notifications.events;

import org.springframework.stereotype.Component;

import com.ngueno.juno.sdk.resources.base.http.JunoBaseService;
import com.ngueno.juno.sdk.resources.notifications.model.EventTypeResources;

@Component
public class JunoEventsService extends JunoBaseService {

    private static final String EVENT_TYPES = "/notifications/event-types";

    public EventTypeResources listEventTypes() {
        return http().get(EVENT_TYPES, EventTypeResources.class);
    }

}
