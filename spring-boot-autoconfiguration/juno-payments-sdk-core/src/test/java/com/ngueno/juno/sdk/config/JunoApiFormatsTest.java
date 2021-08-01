package com.ngueno.juno.sdk.config;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.ngueno.juno.sdk.test.AbstractTest;

import org.junit.jupiter.api.Test;

class JunoApiFormatsTest extends AbstractTest {

    @Test
    void constants() {
        assertEquals("yyyy-MM-dd", JunoApiFormats.API_DATE_PATTERN);
        assertEquals("yyyy-MM-dd HH:mm:ss", JunoApiFormats.API_DATE_TIME_PATTERN);
        assertEquals("yyyy-MM-dd'T'HH:mm:ss.SSSXXX", JunoApiFormats.API_ISO_DATE_TIME_PATTERN);
    }
}
