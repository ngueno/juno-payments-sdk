package com.ngueno.juno.sdk.resources.subscriptions.model;

import java.math.BigDecimal;

import com.ngueno.juno.sdk.resources.base.http.JunoBaseRequest;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class JunoPlanCreateRequest implements JunoBaseRequest {

    private final String name;
    private final BigDecimal amount;
}
