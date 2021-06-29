package com.ngueno.juno.sdk.config;

import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JunoObjectMapperConfig {

    @Bean
    public Jackson2ObjectMapperBuilderCustomizer objectMapperCustomizer() {
        return builder -> {
            builder.failOnUnknownProperties(false);
            builder.failOnEmptyBeans(false);
        };
    }
}
