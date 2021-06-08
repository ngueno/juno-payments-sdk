package com.ngueno.juno.sdk.resources.billpayments.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.ngueno.juno.sdk.resources.base.http.JunoBaseRequest;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@RequiredArgsConstructor
public class JunoBillPaymentPayBillRequest implements JunoBaseRequest {

    private final String numericalBarCode;
    private final String paymentDescription;
    private final String beneficiaryDocument;
    private final LocalDate dueDate;
    private final LocalDate paymentDate;
    private final BigDecimal billAmount;
    private final BigDecimal paidAmount;
}
