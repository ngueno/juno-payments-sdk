package com.ngueno.juno.sdk.resources.base.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter(value = AccessLevel.PRIVATE)
@ToString
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CreditCardDetails extends JunoBaseModel {

    private String creditCardId;
    private String creditCardHash;

    public static CreditCardDetails withCreditCardId(String pCreditCardId) {
        CreditCardDetails creditCardDetails = newInstance();
        creditCardDetails.setCreditCardId(pCreditCardId);
        return creditCardDetails;
    }

    public static CreditCardDetails withCreditCardHash(String pCreditCardHash) {
        CreditCardDetails creditCardDetails = newInstance();
        creditCardDetails.setCreditCardHash(pCreditCardHash);
        return creditCardDetails;
    }

    private static CreditCardDetails newInstance() {
        return new CreditCardDetails();
    }
}