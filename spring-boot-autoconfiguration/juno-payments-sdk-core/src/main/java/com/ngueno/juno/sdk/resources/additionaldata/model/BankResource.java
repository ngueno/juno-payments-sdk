package com.ngueno.juno.sdk.resources.additionaldata.model;

import com.ngueno.juno.sdk.resources.base.resource.JunoBaseResource;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class BankResource extends JunoBaseResource {

    private String number;
    private String name;
}
