package com.ngueno.juno.sdk.resources.creditcards.model;

import com.ngueno.juno.sdk.resources.base.http.JunoBaseRequest;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@RequiredArgsConstructor
public class JunoCreditCardTokenizationRequest implements JunoBaseRequest {

    private final String creditCardHash;
}
