package com.ngueno.juno.sdk.resources.charges.model;

import com.ngueno.juno.sdk.resources.base.resource.JunoBaseEmbeddedResource;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString(callSuper = true)
public class ChargeResources extends JunoBaseEmbeddedResource<ChargeResource> {

    @Override
    protected String getRelation() {
        return "charges";
    }
}
