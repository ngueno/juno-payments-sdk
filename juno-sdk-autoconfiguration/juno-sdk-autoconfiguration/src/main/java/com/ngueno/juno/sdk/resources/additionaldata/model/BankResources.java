package com.ngueno.juno.sdk.resources.additionaldata.model;

import com.ngueno.juno.sdk.resources.base.resource.JunoBaseEmbeddedResource;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString(callSuper = true)
public class BankResources extends JunoBaseEmbeddedResource<BankResource> {

    @Override
    protected String getRelation() {
        return "banks";
    }
}
