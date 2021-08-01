package com.ngueno.juno.sdk.resources.base.model;

import java.math.BigDecimal;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Split extends JunoBaseModel {

    private String recipientToken;
    private BigDecimal amount;
    private BigDecimal percentage;
    private boolean amountRemainder;
    private boolean chargeFee;
}