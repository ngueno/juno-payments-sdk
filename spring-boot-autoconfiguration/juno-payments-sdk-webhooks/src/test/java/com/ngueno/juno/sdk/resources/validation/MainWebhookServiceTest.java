package com.ngueno.juno.sdk.resources.validation;

import static com.ngueno.juno.sdk.test.FixtureHelper.WEBHOOK_SECRET;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.util.ReflectionTestUtils.setField;

import java.util.List;
import java.util.Map;

import com.ngueno.juno.sdk.test.AbstractWebhookTest;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;

class MainWebhookServiceTest extends AbstractWebhookTest {

    @Test
    void annotations() {
        assertTrue(MainWebhookService.class.isAnnotationPresent(ConditionalOnProperty.class));
        ConditionalOnProperty conditionalOnProperty = MainWebhookService.class.getAnnotation(ConditionalOnProperty.class);
        assertThat(List.of(conditionalOnProperty.name()), hasItems("juno.webhooks.main-webhook-service.enabled"));
        assertEquals("true", conditionalOnProperty.havingValue());
    }

    @Test
    void findWebhookSecret() {
        setField(service, "mainWebhookSecret", WEBHOOK_SECRET);
        assertEquals(WEBHOOK_SECRET, service.findWebhookSecret(Map.of()));
    }

    @InjectMocks
    private MainWebhookService service;
}
