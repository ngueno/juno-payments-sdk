package com.ngueno.juno.sdk.resources.base.resource;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.ToString;

@ToString
public abstract class JunoBaseEmbeddedResource<T extends JunoBaseResource> extends JunoBaseResource {

    @JsonProperty("_embedded")
    private Map<String, List<T>> embedded;

    public List<T> getResources() {
        return embedded.get(getRelation());
    }

    protected abstract String getRelation();
}
