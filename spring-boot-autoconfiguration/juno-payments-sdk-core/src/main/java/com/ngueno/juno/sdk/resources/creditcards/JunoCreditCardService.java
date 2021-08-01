package com.ngueno.juno.sdk.resources.creditcards;

import com.ngueno.juno.sdk.resources.base.http.JunoBaseService;
import com.ngueno.juno.sdk.resources.creditcards.model.CreditCardTokenizationResource;
import com.ngueno.juno.sdk.resources.creditcards.model.JunoCreditCardTokenizationRequest;

import org.springframework.stereotype.Component;

@Component
public class JunoCreditCardService extends JunoBaseService {

    public CreditCardTokenizationResource tokenizeCard(JunoCreditCardTokenizationRequest request) {
        return tokenizeCard(getResourceToken(), request);
    }

    public CreditCardTokenizationResource tokenizeCard(String resourceToken, JunoCreditCardTokenizationRequest request) {
        return http().post("/credit-cards/tokenization", resourceToken, request, CreditCardTokenizationResource.class);
    }
}
