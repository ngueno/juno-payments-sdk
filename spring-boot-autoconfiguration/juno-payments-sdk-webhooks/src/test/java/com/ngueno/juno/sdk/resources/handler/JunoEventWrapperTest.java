package com.ngueno.juno.sdk.resources.handler;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.fasterxml.jackson.databind.JsonNode;
import com.ngueno.juno.sdk.test.AbstractWebhookTest;
import com.ngueno.juno.sdk.utils.CalendarUtils;
import com.ngueno.juno.sdk.utils.JacksonUtils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class JunoEventWrapperTest extends AbstractWebhookTest {

    @BeforeEach
    void configure() {
        paymentNotification = helper().getEventPayload();
        wrapper = new JunoEventWrapper(JacksonUtils.readValue(paymentNotification, JsonNode.class));
    }

    @Test
    void getEventId() {
        assertEquals("3cba7c81-3b37-4621-a83c-b0415491385", wrapper.getEventId());
    }

    @Test
    void getEventType() {
        assertEquals("PAYMENT_NOTIFICATION", wrapper.getEventType());
    }

    @Test
    void getTimestamp() {
        assertEquals(CalendarUtils.parseDateTimeIsoFormat("2021-01-21T11:53:02.560-03:00"), wrapper.getTimestamp());
    }

    @Test
    void getData() {
        assertEquals(JacksonUtils.readValue(
                "{\"entityId\":\"pay_A4DA684DC12312312221A84C\",\"entityType\":\"PAYMENT\",\"attributes\":{\"createdOn\":\"2021-01-21 14:53:02\",\"date\":\"2021-01-21 14:53:02\",\"releaseDate\":\"2021-02-22 03:00:00\",\"amount\":\"33.34\",\"fee\":\"2.40\",\"status\":\"CONFIRMED\",\"type\":\"CREDIT_CARD\",\"charge\":{\"id\":\"chr_DF01EC76456A0E9EF30225B901E608D5\",\"code\":136315649,\"reference\":\"reference1\",\"amount\":\"33.34\",\"status\":\"PAID\",\"dueDate\":\"2021-01-24 03:00:00\",\"payer\":{\"name\":\"Teste Juno\",\"document\":\"99999999999\",\"address\":{\"street\":\"Street do evento\",\"number\":\"1\",\"complement\":\"\",\"city\":\"Curitiba\",\"state\":\"PR\",\"postCode\":\"82980190\",\"neighborhood\":\"Cajuru\"}}},\"digitalAccountId\":\"dac_56101832\"}}",
                JsonNode.class), wrapper.getData());
    }

    @Test
    void getEntityId() {
        assertEquals("pay_A4DA684DC12312312221A84C", JunoEventWrapper.getEntityId(wrapper.getData()));
    }

    @Test
    void getEntityType() {
        assertEquals("PAYMENT", JunoEventWrapper.getEntityType(wrapper.getData()));
    }

    @Test
    void getAttributes() {
        assertEquals(JacksonUtils.readValue(
                "{\"createdOn\":\"2021-01-21 14:53:02\",\"date\":\"2021-01-21 14:53:02\",\"releaseDate\":\"2021-02-22 03:00:00\",\"amount\":\"33.34\",\"fee\":\"2.40\",\"status\":\"CONFIRMED\",\"type\":\"CREDIT_CARD\",\"charge\":{\"id\":\"chr_DF01EC76456A0E9EF30225B901E608D5\",\"code\":136315649,\"reference\":\"reference1\",\"amount\":\"33.34\",\"status\":\"PAID\",\"dueDate\":\"2021-01-24 03:00:00\",\"payer\":{\"name\":\"Teste Juno\",\"document\":\"99999999999\",\"address\":{\"street\":\"Street do evento\",\"number\":\"1\",\"complement\":\"\",\"city\":\"Curitiba\",\"state\":\"PR\",\"postCode\":\"82980190\",\"neighborhood\":\"Cajuru\"}}},\"digitalAccountId\":\"dac_56101832\"}",
                JsonNode.class), JunoEventWrapper.getAttributes(wrapper.getData()));
    }

    @Test
    void toStringTest() {
        assertEquals(paymentNotification, wrapper.toString());
    }

    private String paymentNotification;

    private JunoEventWrapper wrapper;
}
