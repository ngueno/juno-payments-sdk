package com.ngueno.juno.sdk.resources.subscriptions.model;

import com.ngueno.juno.sdk.resources.base.resource.JunoBaseEmbeddedResource;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString(callSuper = true)
public class SubscriptionResources extends JunoBaseEmbeddedResource<SubscriptionResource> {

    @Override
    protected String getRelation() {
        return "subscriptions";
    }
}