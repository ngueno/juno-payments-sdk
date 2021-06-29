package com.ngueno.juno.sdk.resources.oauth;

import java.time.Instant;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import lombok.Synchronized;

@Service
public class ProxyJunoOAuthService {

    @Synchronized
    public String getAccessToken() {
        if (needRefresh()) {
            JunoOAuthResource oauthResponse = junoOAuthService.getAccessToken();

            oauthAccessToken = new JunoOAuthAccessToken(oauthResponse.getAccessToken(), //
                    oauthResponse.getExpiresIn(), //
                    Instant.now().getEpochSecond() //
            ); //
        }
        return oauthAccessToken.getAccessToken();
    }

    private boolean needRefresh() {
        return oauthAccessToken == null || oauthAccessToken.isExpired(Instant.now().getEpochSecond());
    }

    private JunoOAuthAccessToken oauthAccessToken;

    @Resource
    private JunoOAuthService junoOAuthService;
}
