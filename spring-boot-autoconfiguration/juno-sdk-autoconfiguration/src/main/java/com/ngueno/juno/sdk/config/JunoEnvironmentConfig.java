package com.ngueno.juno.sdk.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ngueno.juno.sdk.model.environment.JunoEnvironment;

@Configuration
public class JunoEnvironmentConfig {

	@Bean
	public JunoEnvironment junoEnvironment() {
		return new JunoEnvironment(authServerEndpoint, authServerTimeout, apiv2Endpoint, apiv2Timeout);
	}

	@Value("${juno.auth-server.endpoint}")
	private String authServerEndpoint;

	@Value("${juno.auth-server.timeout:10}")
	private int authServerTimeout;

	@Value("${juno.apiv2.endpoint}")
	private String apiv2Endpoint;

	@Value("${juno.apiv2.timeout:10}")
	private int apiv2Timeout;
}
