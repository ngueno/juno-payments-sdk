package com.ngueno.juno.sdk.resources.payments.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.ngueno.juno.sdk.resources.base.model.PaymentStatus;
import com.ngueno.juno.sdk.resources.base.model.PaymentType;
import com.ngueno.juno.sdk.resources.base.resource.JunoBaseResource;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class PaymentResource extends JunoBaseResource {

    private String id;
    private String chargeId;
    private String transactionId;
    private LocalDate date;
    private LocalDate releaseDate;
    private LocalDate paybackDate;
    private BigDecimal amount;
    private BigDecimal fee;
    private PaymentType type;
    private PaymentStatus status;
    private String failReason;
}
