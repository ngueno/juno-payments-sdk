package com.ngueno.juno.sdk.config;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
public class JunoObjectMapperConfig {

    @Bean
    public Jackson2ObjectMapperBuilderCustomizer junoObjectMapperCustomizer() {
        log.debug("[JUNO-SDK] Configuring Jackson2ObjectMapperBuilderCustomizer");

        return builder -> {
            builder.failOnUnknownProperties(false);
            builder.failOnEmptyBeans(false);
        };
    }

    @Bean
    public ObjectMapper junoObjectMapper(@Qualifier("junoObjectMapperCustomizer") Jackson2ObjectMapperBuilderCustomizer customizer) {
        log.debug("[JUNO-SDK] Configuring ObjectMapper");

        Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();
        customizer.customize(builder);
        return builder.build();
    }
}
