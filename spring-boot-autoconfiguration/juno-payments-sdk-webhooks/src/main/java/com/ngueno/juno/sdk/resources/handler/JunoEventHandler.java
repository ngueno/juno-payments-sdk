package com.ngueno.juno.sdk.resources.handler;

public interface JunoEventHandler {

    String getEventType();

    void handleEvent(JunoEventWrapper event);
}
