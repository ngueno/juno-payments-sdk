package com.ngueno.juno.sdk.model.environment;

import java.time.Duration;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class JunoEnvironment {

    private final String authServerEndpoint;
    private final int authServerTimeout;
    private final String apiv2Endpoint;
    private final int apiv2Timeout;

    public Duration getAuthServerTimeoutDuration() {
        return Duration.ofSeconds(authServerTimeout);
    }

    public Duration getApiv2TimeoutDuration() {
        return Duration.ofSeconds(apiv2Timeout);
    }
}
