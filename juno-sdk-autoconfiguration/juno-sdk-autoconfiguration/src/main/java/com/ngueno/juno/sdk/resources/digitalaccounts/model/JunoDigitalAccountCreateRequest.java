package com.ngueno.juno.sdk.resources.digitalaccounts.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import com.ngueno.juno.sdk.resources.base.http.JunoBaseRequest;
import com.ngueno.juno.sdk.resources.base.model.Address;
import com.ngueno.juno.sdk.resources.base.model.BankAccount;
import com.ngueno.juno.sdk.resources.base.model.CompanyMember;
import com.ngueno.juno.sdk.resources.base.model.CompanyType;
import com.ngueno.juno.sdk.resources.base.model.DigitalAccountType;
import com.ngueno.juno.sdk.resources.base.model.LegalRepresentative;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@RequiredArgsConstructor
public class JunoDigitalAccountCreateRequest implements JunoBaseRequest {

    private final DigitalAccountType type = DigitalAccountType.PAYMENT;
    private final String name;
    private final String document;
    private final String email;
    private LocalDate birthDate;
    private final String phone;
    private final Long businessArea;
    private final String linesOfBusiness;
    private CompanyType companyType;
    private LegalRepresentative legalRepresentative;
    private final Address address;
    private final BankAccount bankAccount;
    private boolean emailOptOut;
    private boolean autoTransfer;
    private boolean socialName;
    private BigDecimal monthlyIncomeOrRevenue;
    private String cnae;
    private LocalDate establishmentDate;
    private boolean pep;
    private List<CompanyMember> companyMembers;
}
