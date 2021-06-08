package com.ngueno.juno.sdk.resources.notifications.model;

import com.ngueno.juno.sdk.resources.base.resource.JunoBaseEmbeddedResource;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString(callSuper = true)
public class WebhookResources extends JunoBaseEmbeddedResource<WebhookResource> {

    @Override
    protected String getRelation() {
        return "webhooks";
    }
}
