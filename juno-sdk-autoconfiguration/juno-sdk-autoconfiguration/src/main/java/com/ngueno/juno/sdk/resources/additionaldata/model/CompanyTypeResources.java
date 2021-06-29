package com.ngueno.juno.sdk.resources.additionaldata.model;

import java.util.List;

import com.ngueno.juno.sdk.resources.base.resource.JunoBaseResource;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class CompanyTypeResources extends JunoBaseResource {

    private List<String> companyTypes;
}
