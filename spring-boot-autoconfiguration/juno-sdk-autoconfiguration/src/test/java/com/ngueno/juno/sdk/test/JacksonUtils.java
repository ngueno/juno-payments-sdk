package com.ngueno.juno.sdk.test;

import static org.junit.jupiter.api.Assertions.fail;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ngueno.juno.sdk.config.JunoObjectMapperConfig;

import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

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

    public static <T> T readValue(String value, Class<T> clazz) {
        try {
            return objectMapper().readValue(value, clazz);
        } catch (Exception e) {
            fail("Failed to read resource", e);
        }
        return null;
    }

    private static void configureObjectMapper() {
        Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();
        config.objectMapperCustomizer().customize(builder);
        objectMapper = builder.build();
    }

    private static JunoObjectMapperConfig config = new JunoObjectMapperConfig();
    private static ObjectMapper objectMapper;
}
