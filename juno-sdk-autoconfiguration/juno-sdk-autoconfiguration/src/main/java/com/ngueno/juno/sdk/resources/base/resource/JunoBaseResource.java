package com.ngueno.juno.sdk.resources.base.resource;

import java.util.Map;

import org.springframework.hateoas.Link;
import org.springframework.util.Assert;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;

@Getter
public abstract class JunoBaseResource {

    @JsonProperty("_links")
    private Map<String, Link> links;

    public Link getLinks(String relation) {
        Assert.hasText(relation, "Link relation must not be null or empty!");
        return links.get(relation);
    }
}
