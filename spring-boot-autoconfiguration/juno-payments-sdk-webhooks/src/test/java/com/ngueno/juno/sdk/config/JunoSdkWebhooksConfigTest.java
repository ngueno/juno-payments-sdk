package com.ngueno.juno.sdk.config;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import com.ngueno.juno.sdk.test.AbstractTestSuite;

import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.Ordered;

class JunoSdkWebhooksConfigTest extends AbstractTestSuite {

    @Test
    void configuration() {
        assertTrue(JunoSdkWebhooksConfig.class.isAnnotationPresent(Configuration.class));
    }

    @Test
    void autoConfigureOrder() {
        assertTrue(JunoSdkWebhooksConfig.class.isAnnotationPresent(AutoConfigureOrder.class));
        assertEquals(Ordered.HIGHEST_PRECEDENCE, JunoSdkWebhooksConfig.class.getAnnotation(AutoConfigureOrder.class).value());
    }

    @Test
    void imports() {
        assertTrue(JunoSdkWebhooksConfig.class.isAnnotationPresent(Import.class));
        assertThat(List.of(JunoSdkWebhooksConfig.class.getAnnotation(Import.class).value()), hasItems(JunoSdkBaseConfig.class));
    }

}
