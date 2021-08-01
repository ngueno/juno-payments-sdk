package com.ngueno.juno.sdk.resources.charges.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum JunoChargeListOrder {

    ID("id"),
    DUE_DATE("dueDate"),
    AMOUNT("amount"),
    PAYMENT_DATE("paymentDate");

    private final String order;
}