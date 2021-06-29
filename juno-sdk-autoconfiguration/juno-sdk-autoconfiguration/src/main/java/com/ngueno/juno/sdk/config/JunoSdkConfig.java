package com.ngueno.juno.sdk.config;

import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.Ordered;

@Configuration
@AutoConfigureOrder(Ordered.HIGHEST_PRECEDENCE)
@Import({ //
        JunoSdkScanConfig.class, //
        JunoObjectMapperConfig.class, //
        JunoCredentialsConfig.class, //
        JunoEnvironmentConfig.class //
}) //
public class JunoSdkConfig {
    // Base class for other configurations :)
}
