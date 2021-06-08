package com.ngueno.juno.sdk.resources.payments.model;

import java.util.List;

import com.ngueno.juno.sdk.resources.base.resource.JunoBaseResource;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString(callSuper = true)
public class PaymentResources extends JunoBaseResource {

    private String transactionId;
    private Integer installments;
    private List<PaymentResource> payments;
    private List<PaymentResource> refunds;
}
