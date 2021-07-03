package com.ngueno.juno.sdk.resources.base.model;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.ngueno.juno.sdk.config.JunoApiFormats;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@RequiredArgsConstructor
public class CompanyMember extends JunoBaseModel {

    private final String name;
    private final String document;
    @JsonFormat(shape = Shape.STRING, pattern = JunoApiFormats.API_DATE_PATTERN)
    private final LocalDate birthDate;
}
