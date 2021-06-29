package com.ngueno.juno.sdk.resources.creditcards;

import org.springframework.stereotype.Component;

import com.ngueno.juno.sdk.resources.base.http.JunoBaseService;
import com.ngueno.juno.sdk.resources.creditcards.model.CreditCardTokenizationResource;
import com.ngueno.juno.sdk.resources.creditcards.model.JunoCreditCardTokenizeRequest;

@Component
public class JunoCreditCardService extends JunoBaseService {

    public CreditCardTokenizationResource tokenizeCard(JunoCreditCardTokenizeRequest request) {
        return tokenizeCard(getResourceToken(), request);
    }

    public CreditCardTokenizationResource tokenizeCard(String resourceToken, JunoCreditCardTokenizeRequest request) {
        return http().post("/credit-cards/tokenization", resourceToken, request, CreditCardTokenizationResource.class);
    }
}
