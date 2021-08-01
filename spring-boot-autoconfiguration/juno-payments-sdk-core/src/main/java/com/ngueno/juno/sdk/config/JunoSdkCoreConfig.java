package com.ngueno.juno.sdk.config;

import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.Ordered;

@Configuration
@AutoConfigureOrder(Ordered.HIGHEST_PRECEDENCE)
@Import({ //
        JunoSdkBaseConfig.class, //
        JunoCredentialsConfig.class, //
        JunoEnvironmentConfig.class, //
        JunoHttpConfig.class //
}) //
public class JunoSdkCoreConfig {
    // Base class for other configurations :)
}
