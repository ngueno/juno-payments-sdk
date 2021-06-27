package com.ngueno.juno.sdk.resources.oauth;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@RequiredArgsConstructor
class JunoOAuthAccessToken {

    private static final int SECONDS_UNTIL_EXPIRE = 30;

    private final String accessToken;
    private final int expiresIn;
    private final long generatedAt;

    public boolean isExpired(long currentTime) {
        return expiresIn - (currentTime - generatedAt) <= SECONDS_UNTIL_EXPIRE;
    }

}
