package com.ngueno.juno.sdk.resources.creditcards;

import static org.junit.jupiter.api.Assertions.assertEquals;

import javax.annotation.Resource;

import org.junit.jupiter.api.Test;

import com.ngueno.juno.sdk.resources.creditcards.model.CreditCardTokenizationResource;
import com.ngueno.juno.sdk.resources.creditcards.model.JunoCreditCardTokenizationRequest;
import com.ngueno.juno.sdk.test.AbstractSpringBootTest;

class JunoCreditCardServiceTest extends AbstractSpringBootTest {

    @Test
    void tokenizeCard() {
        JunoCreditCardTokenizationRequest request = new JunoCreditCardTokenizationRequest(
                "38ae847f-197e-471a-b7e8-06cd4eca3b19");

        mockServerManager().expectCreditCardTokenization(request);

        CreditCardTokenizationResource resource = service.tokenizeCard(request);

        assertEquals("813d9718-0107-4593-aafb-14899356d7c0", resource.getCreditCardId());
        assertEquals("9999", resource.getLast4CardNumber());
        assertEquals("12", resource.getExpirationMonth());
        assertEquals("2050", resource.getExpirationYear());
    }

    @Resource
    private JunoCreditCardService service;
}
