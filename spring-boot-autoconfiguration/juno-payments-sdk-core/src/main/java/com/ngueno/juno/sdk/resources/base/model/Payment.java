package com.ngueno.juno.sdk.resources.base.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Payment extends JunoBaseModel {

    private String id;
    private String chargeId;
    private LocalDate date;
    private LocalDate releaseDate;
    private BigDecimal amount;
    private BigDecimal fee;
    private String type;
    private PaymentStatus status;
    private String transactionId;
    private String failReason;
}
