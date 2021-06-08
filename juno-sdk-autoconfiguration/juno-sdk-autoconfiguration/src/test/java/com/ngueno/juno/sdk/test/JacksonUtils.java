package com.ngueno.juno.sdk.test;

import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ngueno.juno.sdk.config.JunoObjectMapperConfig;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class JacksonUtils {

    public static ObjectMapper objectMapper() {
        if (objectMapper == null) {
            configureObjectMapper();
        }
        return objectMapper;
    }

    private static void configureObjectMapper() {
        Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();
        config.objectMapper().customize(builder);
        objectMapper = builder.build();
    }

    private static JunoObjectMapperConfig config = new JunoObjectMapperConfig();
    private static ObjectMapper objectMapper;
}
