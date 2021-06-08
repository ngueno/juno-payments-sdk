package com.ngueno.juno.sdk.resources.oauth;

import static org.junit.jupiter.api.Assertions.assertEquals;

import javax.annotation.Resource;

import org.junit.jupiter.api.Test;

import com.ngueno.juno.sdk.test.AbstractServiceTest;
import com.ngueno.juno.sdk.test.FixtureHelper;

class JunoOAuthServiceTest extends AbstractServiceTest {

    @Test
    void getAccessToken() {
        JunoOAuthResource response = service.getAccessToken();

        assertEquals(FixtureHelper.BEARER_AUTHENTICATION_ACCESS_TOKEN, response.getAccessToken());
        assertEquals("bearer", response.getTokenType());
        assertEquals(86399, response.getExpiresIn());
        assertEquals("mysdk@somerandomemail.com", response.getUserName());
        assertEquals("all", response.getScope());
        assertEquals("XPNkRjUk2vAgAZHhdsh9l5pocPkl", response.getJti());
    }

    @Resource
    private JunoOAuthService service;
}
