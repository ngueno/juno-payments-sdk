package com.ngueno.juno.sdk.resources.digitalaccounts.model;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.ngueno.juno.sdk.config.JunoApiFormats;
import com.ngueno.juno.sdk.resources.base.model.DigitalAccountStatus;
import com.ngueno.juno.sdk.resources.base.model.DigitalAccountType;
import com.ngueno.juno.sdk.resources.base.model.PersonType;
import com.ngueno.juno.sdk.resources.base.resource.JunoBaseResource;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class DigitalAccountResource extends JunoBaseResource {

    private String id;
    private DigitalAccountType type;
    private DigitalAccountStatus status;
    private PersonType personType;
    private String document;
    @JsonFormat(shape = Shape.STRING, pattern = JunoApiFormats.API_ISO_DATE_TIME_PATTERN)
    private LocalDateTime createdOn;
    private String accountNumber;

    public boolean isPerson() {
        return !isCompany();
    }

    public boolean isCompany() {
        return PersonType.J.equals(personType);
    }
}
