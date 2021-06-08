package com.ngueno.juno.sdk.resources.oauth;

import static com.ngueno.juno.sdk.config.JunoApiHeaders.AUTHORIZATION;
import static com.ngueno.juno.sdk.config.JunoApiHeaders.BASIC_AUTHENTICATION;
import static com.ngueno.juno.sdk.config.JunoApiHeaders.CLIENT_CREDENTIALS;
import static com.ngueno.juno.sdk.config.JunoApiHeaders.GRANT_TYPE;

import javax.annotation.Resource;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import com.ngueno.juno.sdk.model.environment.JunoEnvironment;
import com.ngueno.juno.sdk.model.security.JunoApiCredentials;

@Service
class JunoOAuthService {

    public JunoOAuthResource getAccessToken() {
        return WebClient //
                .create(junoEnvironment.getAuthServerEndpoint()) //
                .post() //
                .uri("/oauth/token") //
                .header(AUTHORIZATION, String.format(BASIC_AUTHENTICATION, junoApiCredentials.getAuthorization())) //
                .contentType(MediaType.APPLICATION_FORM_URLENCODED) //
                .body(BodyInserters.fromFormData(GRANT_TYPE, CLIENT_CREDENTIALS)) //
                .retrieve() //
                .bodyToMono(JunoOAuthResource.class) //
                .block(junoEnvironment.getAuthServerTimeoutDuration()); //
    }

    @Resource
    private JunoEnvironment junoEnvironment;

    @Resource
    private JunoApiCredentials junoApiCredentials;
}
