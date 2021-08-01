package com.ngueno.juno.sdk.resources.charges.model;

import com.ngueno.juno.sdk.resources.base.http.JunoBaseRequest;
import com.ngueno.juno.sdk.resources.base.model.Charge;
import com.ngueno.juno.sdk.resources.base.model.ChargeBilling;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@RequiredArgsConstructor
public class JunoChargeCreateRequest implements JunoBaseRequest {

    private final Charge charge;
    private final ChargeBilling billing;
}
