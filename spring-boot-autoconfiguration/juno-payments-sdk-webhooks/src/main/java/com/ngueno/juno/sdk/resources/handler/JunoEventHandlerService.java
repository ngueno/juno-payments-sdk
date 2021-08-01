package com.ngueno.juno.sdk.resources.handler;

import static com.ngueno.juno.sdk.resources.handler.DefaultJunoEventHandler.DEFAULT_HANDLER_EVENT_TYPE;

import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ngueno.juno.sdk.model.error.JunoApiIntegrationException;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class JunoEventHandlerService {

    @PostConstruct
    public void configure() {
        eventHandlersMap = eventHandlers.stream().collect(Collectors.toMap(JunoEventHandler::getEventType, Function.identity()));
    }

    public void handle(String event) {
        JunoEventWrapper eventWrapper = new JunoEventWrapper(convertToJson(event));
        eventHandlersMap.getOrDefault(eventWrapper.getEventType(), eventHandlersMap.get(DEFAULT_HANDLER_EVENT_TYPE)).handleEvent(eventWrapper);
    }

    private JsonNode convertToJson(String event) {
        try {
            return junoObjectMapper.readTree(event);
        } catch (JsonProcessingException e) {
            log.error("[JUNO-SDK] Unable to convert received event {}", event);
            throw new JunoApiIntegrationException("[JUNO-SDK] Unable to convert received event to JsonNode", e);
        }
    }

    @Resource
    private ObjectMapper junoObjectMapper;

    @Resource
    private Set<JunoEventHandler> eventHandlers;

    private Map<String, JunoEventHandler> eventHandlersMap;
}
