package com.ngueno.juno.sdk.resources.payments.model;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ngueno.juno.sdk.resources.base.http.JunoBaseRequest;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class JunoPaymentCaptureRequest implements JunoBaseRequest {

    @JsonIgnore
    private final String paymentId;
    private BigDecimal amount;
}
