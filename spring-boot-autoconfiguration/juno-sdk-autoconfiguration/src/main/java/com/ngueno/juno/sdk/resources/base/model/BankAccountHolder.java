package com.ngueno.juno.sdk.resources.base.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class BankAccountHolder extends JunoBaseModel {

    private final String name;
    private final String document;
}
