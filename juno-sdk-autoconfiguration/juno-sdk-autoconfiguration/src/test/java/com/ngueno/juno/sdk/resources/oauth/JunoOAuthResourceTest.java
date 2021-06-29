package com.ngueno.juno.sdk.resources.oauth;

import static com.ngueno.juno.sdk.test.FixtureHelper.BEARER_AUTHENTICATION_ACCESS_TOKEN;
import static com.ngueno.juno.sdk.test.FixtureHelper.EXPIRES_IN;
import static com.ngueno.juno.sdk.test.FixtureHelper.JTI;
import static com.ngueno.juno.sdk.test.FixtureHelper.SCOPE;
import static com.ngueno.juno.sdk.test.FixtureHelper.TOKEN_TYPE;
import static com.ngueno.juno.sdk.test.FixtureHelper.USER_NAME;
import static com.ngueno.juno.sdk.test.FixtureHelper.getResource;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.ngueno.juno.sdk.test.AbstractTest;
import com.ngueno.juno.sdk.test.JacksonUtils;

import org.junit.jupiter.api.Test;

class JunoOAuthResourceTest extends AbstractTest {

    @Test
    void constructors() {
        JunoOAuthResource resource = create();

        assertEquals(BEARER_AUTHENTICATION_ACCESS_TOKEN, resource.getAccessToken());
        assertEquals(EXPIRES_IN, resource.getExpiresIn());
        assertEquals(JTI, resource.getJti());
        assertEquals(SCOPE, resource.getScope());
        assertEquals(TOKEN_TYPE, resource.getTokenType());
        assertEquals(USER_NAME, resource.getUserName());
    }

    @Test
    void toStringTest() {
        JunoOAuthResource resource = create();
        assertEquals(
                "JunoOAuthResource(accessToken=eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX25hbWUiOiJub3J0b250Z3Vlbm9AaG90bWFpbC5jb20iLCJzY29wZSI6WyJhbGwiXSwiZXhwIjoxNjE2MzY1MjMxLCJqdGkiOiIxcDZ4cVlhdXJxOGYyRmpLbnE5dnhLaUthVDgiLCJjbGllbnRfaWQiOiIyaGZCdXd5UjIxQ1liNUU4In0.ECSOVi1ICvvkw4oV7C3kGVQYRDAsft7u8jJWGqptx8dKqnOEJgUBWl9MB5EjHFWfYmEALFCgmHWYpGgKASDJmk3yJubEg_SaMpxTdsMSdnj3dxvGtXOj3hNi-2CsbPecckya8KjARAFWxLOJxgo813dPHNjSg5cU5JfkjHQaaR5swUap1h_aG9jJ3boRcHl9n_L10DfOlhZcX5NmdtNgpoPhOYRbS7b_Lzromy_kmBVl_d3-sp3zb3Sgio_FbF4A1LDxH44tz2EkiNeBlLRJvD3NzIPY8Uz06e58K1qq8TA6KDp-LY_saQgIrs1ww4GTtAZac1PqQvsHsMbm0JuGZA, tokenType=bearer, expiresIn=86399, userName=mysdk@somerandomemail.com, scope=all, jti=XPNkRjUk2vAgAZHhdsh9l5pocPkl)",
                resource.toString());
    }

    private JunoOAuthResource create() {
        return JacksonUtils.readValue(getResource("mockserver", "oauth", "token", "POST.mock"), JunoOAuthResource.class);
    }
}
