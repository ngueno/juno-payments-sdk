package com.ngueno.juno.sdk.resources.base.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Recipient {

    private String name;
    private String document;
    private BankAccount bankAccount;
}