package com.ngueno.juno.sdk.resources.creditcards.model;

import com.ngueno.juno.sdk.resources.base.resource.JunoBaseResource;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class CreditCardTokenizationResource extends JunoBaseResource {

    private String creditCardId;
    private String last4CardNumber;
    private String expirationMonth;
    private String expirationYear;
}
