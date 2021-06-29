package com.ngueno.juno.sdk.resources.digitalaccounts.model;

import java.time.LocalDate;

import com.ngueno.juno.sdk.resources.base.http.JunoBaseRequest;
import com.ngueno.juno.sdk.resources.base.model.Address;
import com.ngueno.juno.sdk.resources.base.model.BankAccount;
import com.ngueno.juno.sdk.resources.base.model.CompanyType;
import com.ngueno.juno.sdk.resources.base.model.LegalRepresentative;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@RequiredArgsConstructor
public class JunoDigitalAccountPatchRequest implements JunoBaseRequest {

    private final CompanyType companyType;
    private final String name;
    private final LocalDate birthDate;
    private final String linesOfBusiness;
    private final String email;
    private final String phone;
    private final Long businessArea;
    private final String tradingName;
    private final Address address;
    private final BankAccount bankAccount;
    private final LegalRepresentative legalRepresentative;
}
