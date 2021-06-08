package com.ngueno.juno.sdk.resources.billpayments.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.ngueno.juno.sdk.config.JunoApiFormats;
import com.ngueno.juno.sdk.resources.base.model.BillPaymentStatus;
import com.ngueno.juno.sdk.resources.base.model.BillPaymentType;
import com.ngueno.juno.sdk.resources.base.resource.JunoBaseResource;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class BillPaymentResource extends JunoBaseResource {

    private String id;
    private String digitalAccountId;
    private String numericalBarCode;
    private BillPaymentType billType;
    private String paymentDescription;
    private String beneficiaryDocument;
    private LocalDate dueDate;
    private LocalDate paymentDate;
    private BigDecimal billAmount;
    private BigDecimal paidAmount;
    @JsonFormat(shape = Shape.STRING, pattern = JunoApiFormats.API_DATE_TIME_PATTERN)
    private LocalDateTime createdOn;
    private BillPaymentStatus status;
}
