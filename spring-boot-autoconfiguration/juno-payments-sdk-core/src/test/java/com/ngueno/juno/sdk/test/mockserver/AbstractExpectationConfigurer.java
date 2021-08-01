package com.ngueno.juno.sdk.test.mockserver;

import static org.junit.jupiter.api.Assertions.fail;
import static org.mockserver.model.HttpRequest.request;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ngueno.juno.sdk.config.JunoApiHeaders;
import com.ngueno.juno.sdk.test.FixtureHelper;
import com.ngueno.juno.sdk.test.JacksonUtils;

import org.assertj.core.util.Arrays;
import org.mockserver.model.Header;
import org.mockserver.model.HttpRequest;
import org.mockserver.model.MediaType;

public abstract class AbstractExpectationConfigurer {

    protected HttpRequest getRequestExpectationWithoutResourceToken() {
        return getRequestExpectationWithoutResourceToken(MediaType.APPLICATION_JSON);
    }

    protected HttpRequest getRequestExpectationWithoutResourceToken(MediaType contentType) {
        return request() //
                .withHeader(new Header(JunoApiHeaders.CONTENT_TYPE, contentType.toString() + ".*")) //
                .withHeader(new Header(JunoApiHeaders.X_API_VERSION, "2")) //
                .withHeader(new Header(JunoApiHeaders.AUTHORIZATION, FixtureHelper.BEARER_AUTHENTICATION)); //
    }

    protected HttpRequest getRequestExpectation() {
        return getRequestExpectation(MediaType.APPLICATION_JSON);
    }

    protected HttpRequest getRequestExpectation(MediaType contentType) {
        return getRequestExpectationWithoutResourceToken(contentType) //
                .withHeader(new Header(JunoApiHeaders.X_RESOURCE_TOKEN, FixtureHelper.RESOURCE_TOKEN)); //
    }

    protected String toJson(Object object) {
        try {
            return JacksonUtils.objectMapper().writeValueAsString(object);
        } catch (JsonProcessingException e) {
            fail("Failed to convert object to json", e);
        }
        return null;
    }

    protected String getResource(String... resources) {
        return FixtureHelper.getResource(toMockServerResource(resources));
    }

    private String[] toMockServerResource(String... resources) {
        List<Object> resourcesList = Arrays.asList(resources);
        resourcesList.add(0, "mockserver");
        return resourcesList.toArray(new String[resourcesList.size()]);
    }
}
