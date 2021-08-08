package com.ngueno.juno.sdk.resources.handler;

import java.time.LocalDateTime;

import com.fasterxml.jackson.databind.JsonNode;
import com.ngueno.juno.sdk.utils.CalendarUtils;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class JunoEventWrapper {

    private final JsonNode eventJson;

    @Override
    public String toString() {
        return eventJson.toPrettyString();
    }

    public String getEventId() {
        return eventJson.get("eventId").asText();
    }

    public String getEventType() {
        return eventJson.get("eventType").asText();
    }

    public LocalDateTime getTimestamp() {
        return CalendarUtils.parseDateTimeIsoFormat(eventJson.get("timestamp").asText());
    }

    public JsonNode getData() {
        return getData(0);
    }

    public JsonNode getData(int index) {
        return eventJson.get("data").get(index);
    }

    public static String getEntityId(JsonNode data) {
        return data.get("entityId").asText();
    }

    public static String getEntityType(JsonNode data) {
        return data.get("entityType").asText();
    }

    public static JsonNode getAttributes(JsonNode data) {
        return data.get("attributes");
    }
}
