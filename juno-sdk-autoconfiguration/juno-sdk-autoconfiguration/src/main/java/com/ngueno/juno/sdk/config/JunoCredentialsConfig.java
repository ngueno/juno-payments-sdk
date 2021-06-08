package com.ngueno.juno.sdk.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ngueno.juno.sdk.model.security.JunoApiCredentials;

@Configuration
public class JunoCredentialsConfig {

	@Bean
	public JunoApiCredentials apiCredentials() {
		return new JunoApiCredentials(clientId, clientSecret, resourceToken, publicKey);
	}

	@Value("${juno.credentials.clientId}")
	private String clientId;

	@Value("${juno.credentials.clientSecret}")
	private String clientSecret;

	@Value("${juno.credentials.resourceToken}")
	private String resourceToken;

	@Value("${juno.credentials.publicKey:#{null}}")
	private String publicKey;
}
