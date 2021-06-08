package com.ngueno.juno.sdk.resources.notifications.model;

import java.util.List;

import com.ngueno.juno.sdk.resources.base.resource.JunoBaseResource;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString(callSuper = true)
public class EventTypeResources extends JunoBaseResource {

    private List<EventTypeResource> eventTypes;
}
