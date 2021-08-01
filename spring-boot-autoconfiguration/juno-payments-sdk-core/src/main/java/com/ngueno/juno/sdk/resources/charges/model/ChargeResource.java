package com.ngueno.juno.sdk.resources.charges.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.ngueno.juno.sdk.resources.base.model.ChargeStatus;
import com.ngueno.juno.sdk.resources.base.resource.JunoBaseResource;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ChargeResource extends JunoBaseResource {

    private String id;
    private Long code;
    private String reference;
    private LocalDate dueDate;
    private String link;
    private String checkoutUrl;
    private String installmentLink;
    private String payNumber;
    private BigDecimal amount;
    private ChargeStatus status;

}
