package com.ngueno.juno.sdk.resources.base.http;

import javax.annotation.Resource;

import com.ngueno.juno.sdk.model.security.JunoApiCredentials;

import org.springframework.stereotype.Service;

@Service
public abstract class JunoBaseService {

    protected JunoHttpService http() {
        return http;
    }

    protected String getResourceToken() {
        return junoApiCredentials.getResourceToken();
    }

    protected String expandId(String uri, String value) {
        return expandUri(uri, "{id}", value);
    }

    protected String expandUri(String uri, String placeHolder, String value) {
        return uri.replace(placeHolder, value);
    }

    @Resource
    private JunoApiCredentials junoApiCredentials;

    @Resource
    private JunoHttpService http;
}
