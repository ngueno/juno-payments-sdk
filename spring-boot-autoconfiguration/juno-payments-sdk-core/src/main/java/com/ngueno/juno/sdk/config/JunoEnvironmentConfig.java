package com.ngueno.juno.sdk.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ngueno.juno.sdk.model.environment.JunoEnvironment;

@Slf4j
@Configuration
public class JunoEnvironmentConfig {

	@Bean
	public JunoEnvironment junoEnvironment() {
		log.debug("[JUNO-SDK] Currently working with the following endpoints:");
		log.debug("[JUNO-SDK] Authorization Server:");
		log.debug("[JUNO-SDK] Endpoint: {}", authServerEndpoint);
		log.debug("[JUNO-SDK] Timeout (in seconds): {}", authServerTimeout);
		log.debug("[JUNO-SDK] APIv2:");
		log.debug("[JUNO-SDK] Endpoint: {}", apiv2Endpoint);
		log.debug("[JUNO-SDK] Timeout (in seconds): {}", apiv2Timeout);

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
