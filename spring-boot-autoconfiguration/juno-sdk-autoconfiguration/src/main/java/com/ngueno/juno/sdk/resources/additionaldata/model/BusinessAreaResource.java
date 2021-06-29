package com.ngueno.juno.sdk.resources.additionaldata.model;

import com.ngueno.juno.sdk.resources.base.resource.JunoBaseResource;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class BusinessAreaResource extends JunoBaseResource {

    private Long code;
    private String activity;
    private String category;
}
