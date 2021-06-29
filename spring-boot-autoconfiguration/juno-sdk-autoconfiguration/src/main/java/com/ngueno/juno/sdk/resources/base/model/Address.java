package com.ngueno.juno.sdk.resources.base.model;

import org.apache.commons.lang3.StringUtils;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Address {

    private final String street;
    private final String number;
    private final String city;
    private final String state;
    private final String postCode;
    private String complement;
    private String neighborhood;

    public String getNumber() {
        return StringUtils.isNotBlank(number) ? number : "N/A";
    }
}
