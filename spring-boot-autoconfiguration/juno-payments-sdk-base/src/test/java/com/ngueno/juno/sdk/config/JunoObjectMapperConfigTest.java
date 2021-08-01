package com.ngueno.juno.sdk.config;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import javax.annotation.Resource;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.ngueno.juno.sdk.test.AbstractTestSuite;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockserver.junit.jupiter.MockServerExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

@ExtendWith(MockServerExtension.class)
@SpringBootTest(webEnvironment = WebEnvironment.NONE, classes = JunoObjectMapperConfig.class)
class JunoObjectMapperConfigTest extends AbstractTestSuite {

    @Test
    void customizer() {
        assertNotNull(config.junoObjectMapperCustomizer());
    }

    @Test
    void objectMapper() {
        Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();
        config.junoObjectMapperCustomizer().customize(builder);
        ObjectMapper objectMapper = builder.build();
        assertFalse(objectMapper.isEnabled(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES));
        assertFalse(objectMapper.isEnabled(SerializationFeature.FAIL_ON_EMPTY_BEANS));
    }

    @Resource
    private JunoObjectMapperConfig config;
}
