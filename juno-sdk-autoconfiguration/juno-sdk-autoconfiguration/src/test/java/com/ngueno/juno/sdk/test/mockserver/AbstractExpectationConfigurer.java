package com.ngueno.juno.sdk.test.mockserver;

import static org.junit.jupiter.api.Assertions.fail;
import static org.mockserver.model.HttpRequest.request;

import java.util.List;

import org.assertj.core.util.Arrays;
import org.mockserver.model.Header;
import org.mockserver.model.HttpRequest;
import org.mockserver.model.MediaType;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ngueno.juno.sdk.config.JunoApiHeaders;
import com.ngueno.juno.sdk.test.FixtureHelper;
import com.ngueno.juno.sdk.test.JacksonUtils;

public abstract class AbstractExpectationConfigurer {

    protected HttpRequest getRequestExpectationWithoutResourceToken() {
        return request() //
                .withContentType(MediaType.APPLICATION_JSON) //
                .withHeader(new Header(JunoApiHeaders.X_API_VERSION, "2")) //
                .withHeader(new Header(JunoApiHeaders.AUTHORIZATION, FixtureHelper.BEARER_AUTHENTICATION)); //
    }

    protected HttpRequest getRequestExpectation() {
        return getRequestExpectationWithoutResourceToken() //
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
