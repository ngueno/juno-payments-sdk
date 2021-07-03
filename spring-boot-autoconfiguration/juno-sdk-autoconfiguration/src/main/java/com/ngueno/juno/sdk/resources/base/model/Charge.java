package com.ngueno.juno.sdk.resources.base.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Charge extends JunoBaseModel {

    private final String description;
    private String pixKey;
    private List<String> references;

    private BigDecimal amount;
    private BigDecimal totalAmount;
    private LocalDate dueDate;
    private Integer installments;
    private Integer maxOverdueDays;
    private BigDecimal fine;
    private BigDecimal interest;
    private BigDecimal discountAmount;
    private BigDecimal discountDays;

    private List<PaymentType> paymentTypes;
    private boolean paymentAdvance;
    private String feeSchemaToken;
    private List<Split> split = new ArrayList<>();
}