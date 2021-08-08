package com.ngueno.juno.sdk.resources.validation;

import static com.ngueno.juno.sdk.test.FixtureHelper.EVENT_SIGNATURE;
import static com.ngueno.juno.sdk.test.FixtureHelper.X_SIGNATURE;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.springframework.test.util.ReflectionTestUtils.setField;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;

import com.ngueno.juno.sdk.model.error.JunoApiIntegrationException;
import com.ngueno.juno.sdk.test.AbstractWebhookTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.mock.web.MockFilterChain;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

class JunoEventValidationFilterTest extends AbstractWebhookTest {

    @Test
    void annotations() {
        assertTrue(JunoEventValidationFilter.class.isAnnotationPresent(ConditionalOnProperty.class));
        ConditionalOnProperty conditionalOnProperty = JunoEventValidationFilter.class.getAnnotation(ConditionalOnProperty.class);
        assertThat(List.of(conditionalOnProperty.name()), hasItems("juno.webhooks.endpoint.enabled"));
        assertEquals("true", conditionalOnProperty.havingValue());
    }

    @Test
    void validateFilterOtherApplicationUrls() {
        configureServletPath("/some-random-other-endpoint");
        configureFilterEndpoint("/filtered-endpoint-different-than-the-requested");
        doFilter();
    }

    @Test
    void validateFilterWithoutXSignature() {
        expectMatchingFilterEndpoint();
        assertThrows(JunoApiIntegrationException.class, this::doFilter, "Event signature not found");
    }

    @Test
    void validateFilterWithInvalidXSignature() {
        expectMatchingFilterEndpoint();
        configureSignature();
        doThrow(new JunoApiIntegrationException("Some error")).when(service).validateEvent(Map.of(), payload, EVENT_SIGNATURE);
        assertThrows(JunoApiIntegrationException.class, this::doFilter, "Failure validating the event body");
        verifiyEventValidation();
    }

    @Test
    void validateFilterWithValidEvent() {
        expectMatchingFilterEndpoint();
        configureSignature();
        doFilter();
        verifiyEventValidation();
    }

    private void doFilter() {
        try {
            filter.doFilter(request, response, filterChain);
        } catch (IOException | ServletException e) {
            fail("Test misconfiguration, this should not fail :)", e);
        }
    }

    @BeforeEach
    void configure() {
        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();
        filterChain = new MockFilterChain();

        payload = helper().getEventPayload().toString();
        request.setContent(payload.getBytes());
    }

    private void expectMatchingFilterEndpoint() {
        configureFilterEndpoint("/filtered");
        configureServletPath("/filtered");
    }

    private void configureFilterEndpoint(String filterEndpoint) {
        setField(filter, "junoWebhooksEndpoint", filterEndpoint);
    }

    private void configureServletPath(String servletPath) {
        request.setServletPath(servletPath);
    }

    private void configureSignature() {
        request.addHeader(X_SIGNATURE, EVENT_SIGNATURE);
    }

    private void verifiyEventValidation() {
        verify(service).validateEvent(Mockito.any(), Mockito.eq(payload), Mockito.eq(EVENT_SIGNATURE));
    }

    private MockHttpServletRequest request;
    private MockHttpServletResponse response;
    private MockFilterChain filterChain;

    private String payload;

    @Mock
    private JunoEventValidationService service;

    @InjectMocks
    private JunoEventValidationFilter filter;
}
