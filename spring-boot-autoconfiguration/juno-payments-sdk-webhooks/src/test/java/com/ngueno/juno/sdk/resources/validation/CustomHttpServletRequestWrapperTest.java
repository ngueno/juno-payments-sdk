package com.ngueno.juno.sdk.resources.validation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;

import javax.servlet.ServletInputStream;

import com.fasterxml.jackson.databind.JsonNode;
import com.ngueno.juno.sdk.test.AbstractWebhookTest;
import com.ngueno.juno.sdk.test.JacksonUtils;

import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;

class CustomHttpServletRequestWrapperTest extends AbstractWebhookTest {

    @Test
    void constructors() throws IOException {
        paymentEvent = helper().getEventPayload();

        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setContent(paymentEvent.getBytes());
        CustomHttpServletRequestWrapper customServletRequest = new CustomHttpServletRequestWrapper(request);

        assertCaching(customServletRequest);
        assertCaching(customServletRequest);
    }

    private void assertCaching(CustomHttpServletRequestWrapper customServletRequest) throws IOException {
        assertEquals(paymentEvent, customServletRequest.getCachedRequestAsString());
        assertEquals(JacksonUtils.readValue(paymentEvent, JsonNode.class), customServletRequest.getCachedRequestAsJson());
        assertEquals("{", customServletRequest.getReader().readLine()); // First line of the Json

        assertCachingInputStream(customServletRequest);
    }

    private void assertCachingInputStream(CustomHttpServletRequestWrapper customServletRequest) throws IOException {
        ServletInputStream inputStream = customServletRequest.getInputStream();
        assertTrue(inputStream instanceof CustomHttpServletRequestWrapper.CachedBodyServletInputStream);
        assertTrue(inputStream.isReady());
        assertFalse(inputStream.isFinished());
        assertThrows(UnsupportedOperationException.class, () -> inputStream.setReadListener(null));
        assertEquals(123, inputStream.read());
    }

    private String paymentEvent;
}
