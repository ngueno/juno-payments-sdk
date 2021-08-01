package com.ngueno.juno.sdk.resources.handler;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class DefaultJunoEventHandler implements JunoEventHandler {

    public static final String DEFAULT_HANDLER_EVENT_TYPE = "default";

    @Override
    public String getEventType() {
        return DEFAULT_HANDLER_EVENT_TYPE;
    }

    @Override
    public void handleEvent(JunoEventWrapper event) {
        log.info("[JUNO-SDK] Event: {}", event.toString());
        log.info("[JUNO-SDK] Event handled by default handler, consider implementing your own handler :)");
    }
}
