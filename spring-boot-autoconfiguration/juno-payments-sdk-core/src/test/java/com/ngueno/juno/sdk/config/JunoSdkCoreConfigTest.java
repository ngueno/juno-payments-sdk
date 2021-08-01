package com.ngueno.juno.sdk.config;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import com.ngueno.juno.sdk.test.AbstractCoreTest;

import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.Ordered;

class JunoSdkCoreConfigTest extends AbstractCoreTest {

    @Test
    void configuration() {
        assertTrue(JunoSdkCoreConfig.class.isAnnotationPresent(Configuration.class));
    }

    @Test
    void autoConfigureOrder() {
        assertTrue(JunoSdkCoreConfig.class.isAnnotationPresent(AutoConfigureOrder.class));
        assertEquals(Ordered.HIGHEST_PRECEDENCE, JunoSdkCoreConfig.class.getAnnotation(AutoConfigureOrder.class).value());
    }

    @Test
    void imports() {
        assertTrue(JunoSdkCoreConfig.class.isAnnotationPresent(Import.class));
        assertThat(List.of(JunoSdkCoreConfig.class.getAnnotation(Import.class).value()),
                hasItems(JunoSdkBaseConfig.class, JunoCredentialsConfig.class, JunoEnvironmentConfig.class));
    }

}
