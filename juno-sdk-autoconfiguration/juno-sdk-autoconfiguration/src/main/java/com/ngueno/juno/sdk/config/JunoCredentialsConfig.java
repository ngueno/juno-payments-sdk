package com.ngueno.juno.sdk.config;

import com.ngueno.juno.sdk.model.security.JunoApiCredentials;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JunoCredentialsConfig {

    @Bean
    public JunoApiCredentials apiCredentials() {
        return new JunoApiCredentials(clientId, clientSecret, resourceToken, publicToken);
    }

    @Value("${juno.credentials.clientId}")
    private String clientId;

    @Value("${juno.credentials.clientSecret}")
    private String clientSecret;

    @Value("${juno.credentials.resourceToken}")
    private String resourceToken;

    @Value("${juno.credentials.publicToken:#{null}}")
    private String publicToken;
}
