package com.ngueno.juno.sdk.resources.payments.model;

import com.ngueno.juno.sdk.resources.base.http.JunoBaseRequest;
import com.ngueno.juno.sdk.resources.base.model.CreditCardDetails;
import com.ngueno.juno.sdk.resources.base.model.PaymentBilling;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class JunoPaymentCreateRequest implements JunoBaseRequest {

    private final String chargeId;
    private final PaymentBilling billing;
    private final CreditCardDetails creditCardDetails;
}
