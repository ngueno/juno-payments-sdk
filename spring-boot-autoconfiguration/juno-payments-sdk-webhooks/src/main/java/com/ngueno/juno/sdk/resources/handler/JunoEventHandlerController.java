package com.ngueno.juno.sdk.resources.handler;

import javax.annotation.Resource;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("${juno.webhooks.endpoint.request-mapping:/juno/events}")
@ConditionalOnProperty(name = "juno.webhooks.endpoint.enabled", havingValue = "true")
public class JunoEventHandlerController {

    @PostMapping
    public void handleEvent(@RequestBody String event) {
        handler.handle(event);
    }

    @Resource
    private JunoEventHandlerService handler;
}
