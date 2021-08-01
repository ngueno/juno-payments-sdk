package com.ngueno.juno.sdk.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({ //
        JunoSdkScanConfig.class, //
        JunoObjectMapperConfig.class, //
}) //
public class JunoSdkBaseConfig {
    // Base class for other configurations :)
}
