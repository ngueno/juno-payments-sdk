package com.ngueno.juno.sdk.config;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import com.ngueno.juno.sdk.test.AbstractTest;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.ComponentScan;

class JunoSdkScanConfigTest extends AbstractTest {

    @Test
    void componentScan() {
        assertTrue(JunoSdkScanConfig.class.isAnnotationPresent(ComponentScan.class));
        assertThat(List.of(JunoSdkScanConfig.class.getAnnotation(ComponentScan.class).basePackages()), hasItems("com.ngueno.juno.sdk"));
    }

}
