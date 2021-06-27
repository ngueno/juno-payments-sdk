package com.ngueno.juno.sdk.resources.oauth;

import static com.ngueno.juno.sdk.test.FixtureHelper.BEARER_AUTHENTICATION_ACCESS_TOKEN;
import static com.ngueno.juno.sdk.test.FixtureHelper.EXPIRES_IN;
import static com.ngueno.juno.sdk.test.FixtureHelper.GENERATED_AT;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.ngueno.juno.sdk.test.AbstractTest;

import org.junit.jupiter.api.Test;

class JunoOAuthAccessTokenTest extends AbstractTest {

    @Test
    void constructors() {
        JunoOAuthAccessToken token = create();
        assertEquals(BEARER_AUTHENTICATION_ACCESS_TOKEN, token.getAccessToken());
        assertEquals(EXPIRES_IN, token.getExpiresIn());
        assertEquals(GENERATED_AT, token.getGeneratedAt());
    }

    @Test
    void isExpired() {
        JunoOAuthAccessToken token = create();
        assertTrue(token.isExpired(GENERATED_AT + EXPIRES_IN));
        assertTrue(token.isExpired(GENERATED_AT + (EXPIRES_IN - 30))); // We will refresh the token before it expires
        assertFalse(token.isExpired(GENERATED_AT + (EXPIRES_IN - 31)));
        assertFalse(token.isExpired(GENERATED_AT));
    }

    @Test
    void toStringTest() {
        JunoOAuthAccessToken token = create();
        assertEquals(
                "JunoOAuthAccessToken(accessToken=eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX25hbWUiOiJub3J0b250Z3Vlbm9AaG90bWFpbC5jb20iLCJzY29wZSI6WyJhbGwiXSwiZXhwIjoxNjE2MzY1MjMxLCJqdGkiOiIxcDZ4cVlhdXJxOGYyRmpLbnE5dnhLaUthVDgiLCJjbGllbnRfaWQiOiIyaGZCdXd5UjIxQ1liNUU4In0.ECSOVi1ICvvkw4oV7C3kGVQYRDAsft7u8jJWGqptx8dKqnOEJgUBWl9MB5EjHFWfYmEALFCgmHWYpGgKASDJmk3yJubEg_SaMpxTdsMSdnj3dxvGtXOj3hNi-2CsbPecckya8KjARAFWxLOJxgo813dPHNjSg5cU5JfkjHQaaR5swUap1h_aG9jJ3boRcHl9n_L10DfOlhZcX5NmdtNgpoPhOYRbS7b_Lzromy_kmBVl_d3-sp3zb3Sgio_FbF4A1LDxH44tz2EkiNeBlLRJvD3NzIPY8Uz06e58K1qq8TA6KDp-LY_saQgIrs1ww4GTtAZac1PqQvsHsMbm0JuGZA, expiresIn=86399, generatedAt=1000)",
                token.toString());
    }

    private JunoOAuthAccessToken create() {
        return new JunoOAuthAccessToken(BEARER_AUTHENTICATION_ACCESS_TOKEN, EXPIRES_IN, GENERATED_AT);
    }
}
