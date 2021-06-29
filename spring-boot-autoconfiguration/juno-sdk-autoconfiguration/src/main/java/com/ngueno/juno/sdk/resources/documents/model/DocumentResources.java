package com.ngueno.juno.sdk.resources.documents.model;

import com.ngueno.juno.sdk.resources.base.resource.JunoBaseEmbeddedResource;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString(callSuper = true)
public class DocumentResources extends JunoBaseEmbeddedResource<DocumentResource> {

    @Override
    protected String getRelation() {
        return "documents";
    }
}
