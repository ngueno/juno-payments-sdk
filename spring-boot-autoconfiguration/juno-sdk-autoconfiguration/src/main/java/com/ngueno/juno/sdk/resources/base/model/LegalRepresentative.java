package com.ngueno.juno.sdk.resources.base.model;

import java.time.LocalDate;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@RequiredArgsConstructor
public class LegalRepresentative {

    private final String name;
    private final String document;
    private final LocalDate birthDate;
    private final String motherName;
    private final LegalRepresentativeType type;
}
