package com.ngueno.juno.sdk.resources.payments.model;

import java.math.BigDecimal;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ngueno.juno.sdk.resources.base.http.JunoBaseRequest;
import com.ngueno.juno.sdk.resources.base.model.Split;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class JunoPaymentRefundRequest implements JunoBaseRequest {

    @JsonIgnore
    private final String paymentId;
    private BigDecimal amount;
    private List<Split> split;
}
