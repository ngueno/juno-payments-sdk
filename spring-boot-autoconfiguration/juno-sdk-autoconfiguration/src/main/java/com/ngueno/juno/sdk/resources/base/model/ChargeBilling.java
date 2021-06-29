package com.ngueno.juno.sdk.resources.base.model;

import java.time.LocalDate;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class ChargeBilling {

    private final String name;
    private final String document;
    private final String email;
    private final Address address;
    private String secondaryEmail;
    private String phone;
    private LocalDate birthDate;
    private boolean notify;

}