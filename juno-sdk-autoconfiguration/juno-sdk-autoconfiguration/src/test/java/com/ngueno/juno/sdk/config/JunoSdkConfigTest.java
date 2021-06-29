package com.ngueno.juno.sdk.config;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import com.ngueno.juno.sdk.test.AbstractTest;

import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.Ordered;

class JunoSdkConfigTest extends AbstractTest {

    @Test
    void configuration() {
        assertTrue(JunoSdkConfig.class.isAnnotationPresent(Configuration.class));
    }

    @Test
    void autoConfigureOrder() {
        assertTrue(JunoSdkConfig.class.isAnnotationPresent(AutoConfigureOrder.class));
        assertEquals(Ordered.HIGHEST_PRECEDENCE, JunoSdkConfig.class.getAnnotation(AutoConfigureOrder.class).value());
    }

    @Test
    void imports() {
        assertTrue(JunoSdkConfig.class.isAnnotationPresent(Import.class));
        assertThat(List.of(JunoSdkConfig.class.getAnnotation(Import.class).value()),
                hasItems(JunoSdkScanConfig.class, JunoObjectMapperConfig.class, JunoCredentialsConfig.class, JunoEnvironmentConfig.class));
    }

}
