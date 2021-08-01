package com.ngueno.juno.sdk.resources.validation;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ngueno.juno.sdk.config.JunoApiHeaders;
import com.ngueno.juno.sdk.model.error.JunoApiIntegrationException;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@ConditionalOnProperty(name = "juno.webhooks.endpoint.enabled", havingValue = "true")
public class JunoEventValidationFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws IOException, ServletException {
        CustomHttpServletRequestWrapper cachingRequestWrapper = new CustomHttpServletRequestWrapper(request);

        String eventSignature = request.getHeader(JunoApiHeaders.X_SIGNATURE);

        if (StringUtils.isBlank(eventSignature)) {
            throw new JunoApiIntegrationException("Event signature not found");
        }

        try {
            log.debug("[JUNO-SDK] Validating event signature");
            service.validateEvent(request.getParameterMap(), cachingRequestWrapper.getCachedRequestAsString(), eventSignature);
            log.debug("[JUNO-SDK] Signature matches, trusting event");
        } catch (Exception ex) {
            throw new JunoApiIntegrationException("Failure validating the event body", ex);
        }

        filterChain.doFilter(cachingRequestWrapper, response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        return !request.getServletPath().contains(junoWebhooksEndpoint);
    }

    @Autowired
    private JunoEventValidationService service;

    @Value("${juno.webhooks.endpoint.request-mapping}")
    private String junoWebhooksEndpoint;
}
