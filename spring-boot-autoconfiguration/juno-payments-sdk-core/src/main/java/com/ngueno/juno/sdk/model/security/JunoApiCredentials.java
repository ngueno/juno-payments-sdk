package com.ngueno.juno.sdk.model.security;

import static java.nio.charset.StandardCharsets.UTF_8;

import org.springframework.util.Base64Utils;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class JunoApiCredentials {

    private static final String AUTH_PLACEHOLDER = "%s:%s";

    private final String clientId;
    private final String clientSecret;

    @Getter
    private final String resourceToken;
    @Getter
    private final String publicToken;

    /**
     * Base64(juno.credentials.clientId:juno.credentials.clientSecret
     */
    public String getAuthorization() {
        return Base64Utils.encodeToString(String.format(AUTH_PLACEHOLDER, clientId, clientSecret).getBytes(UTF_8));
    }
}
