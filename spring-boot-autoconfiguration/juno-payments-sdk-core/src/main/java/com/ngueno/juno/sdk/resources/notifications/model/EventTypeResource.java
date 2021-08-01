package com.ngueno.juno.sdk.resources.notifications.model;

import com.ngueno.juno.sdk.resources.base.model.EventTypeStatus;
import com.ngueno.juno.sdk.resources.base.resource.JunoBaseResource;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class EventTypeResource extends JunoBaseResource {

    private String id;
    private String name;
    private String label;
    private EventTypeStatus status;
}
