package com.ngueno.juno.sdk.resources.oauth;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
class JunoOAuthResource {

    @JsonProperty("access_token")
    private String accessToken;
    @JsonProperty("token_type")
    private String tokenType;
    @JsonProperty("expires_in")
    private int expiresIn;
    @JsonProperty("user_name")
    private String userName;

    private String scope;

    private String jti;
}
