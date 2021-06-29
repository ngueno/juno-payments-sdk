package com.ngueno.juno.sdk.resources.base.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class PaymentBilling {

    private final String email;
    private final Address address;
    private boolean delayed;

}