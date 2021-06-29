package com.ngueno.juno.sdk.resources.notifications.model;

import com.ngueno.juno.sdk.resources.base.resource.JunoBaseEmbeddedResource;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString(callSuper = true)
public class EventTypeResources extends JunoBaseEmbeddedResource<EventTypeResource> {

    @Override
    protected String getRelation() {
        return "eventTypes";
    }
}
