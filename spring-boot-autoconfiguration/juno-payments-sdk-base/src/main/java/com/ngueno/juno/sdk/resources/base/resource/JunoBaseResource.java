package com.ngueno.juno.sdk.resources.base.resource;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.ToString;
import org.springframework.util.Assert;

import java.util.Map;

@Getter
@ToString
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public abstract class JunoBaseResource {

    @JsonProperty("_links")
    private Map<String, Link> links;

    public Link getLinks(String relation) {
        Assert.hasText(relation, "Link relation must not be null or empty!");
        return links.get(relation);
    }
}
