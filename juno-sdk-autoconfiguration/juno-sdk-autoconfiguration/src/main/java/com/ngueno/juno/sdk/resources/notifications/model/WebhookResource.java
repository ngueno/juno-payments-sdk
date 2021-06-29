package com.ngueno.juno.sdk.resources.notifications.model;

import java.util.List;

import com.ngueno.juno.sdk.resources.base.model.WebhookStatus;
import com.ngueno.juno.sdk.resources.base.resource.JunoBaseResource;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class WebhookResource extends JunoBaseResource {

    private String id;
    private String url;
    private String secret;
    private WebhookStatus status;
    private List<EventTypeResource> eventTypes;
}
