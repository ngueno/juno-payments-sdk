package com.ngueno.juno.sdk.resources.additionaldata.model;

import com.ngueno.juno.sdk.resources.base.resource.JunoBaseEmbeddedResource;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString(callSuper = true)
public class BusinessAreaResources extends JunoBaseEmbeddedResource<BusinessAreaResource> {

    @Override
    protected String getRelation() {
        return "businessAreas";
    }
}
