package com.ngueno.juno.sdk.resources.base.model;

import java.math.BigDecimal;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Split {

    private String recipientToken;
    private BigDecimal amount;
    private BigDecimal percentage;
    private boolean amountRemainder;
    private boolean chargeFee;
}