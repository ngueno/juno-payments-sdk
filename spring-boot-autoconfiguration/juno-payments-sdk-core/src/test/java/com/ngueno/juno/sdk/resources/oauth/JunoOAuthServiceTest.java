package com.ngueno.juno.sdk.resources.oauth;

import static org.junit.jupiter.api.Assertions.assertEquals;

import javax.annotation.Resource;

import com.ngueno.juno.sdk.test.AbstractSpringBootTest;
import com.ngueno.juno.sdk.test.FixtureHelper;

import org.junit.jupiter.api.Test;

class JunoOAuthServiceTest extends AbstractSpringBootTest {

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
