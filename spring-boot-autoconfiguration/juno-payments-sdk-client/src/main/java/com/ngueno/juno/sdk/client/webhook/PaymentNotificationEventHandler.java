package com.ngueno.juno.sdk.client.webhook;

import com.ngueno.juno.sdk.resources.handler.JunoEventHandler;
import com.ngueno.juno.sdk.resources.handler.JunoEventWrapper;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PaymentNotificationEventHandler implements JunoEventHandler {

    @Override
    public String getEventType() {
        return "PAYMENT_NOTIFICATION";
    }

    @Override
    public void handleEvent(JunoEventWrapper event) {
        log.info("[JUNO-CLIENT] Event: {}", event.toString());
        log.info("[JUNO-CLIENT] Event handled by client custom handler :)");
    }

}
