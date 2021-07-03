package com.ngueno.juno.sdk.resources.digitalaccounts.model;

import java.time.LocalDate;

import com.ngueno.juno.sdk.resources.base.http.JunoBaseRequest;
import com.ngueno.juno.sdk.resources.base.model.Address;
import com.ngueno.juno.sdk.resources.base.model.BankAccount;
import com.ngueno.juno.sdk.resources.base.model.CompanyType;
import com.ngueno.juno.sdk.resources.base.model.LegalRepresentative;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class JunoDigitalAccountPatchRequest implements JunoBaseRequest {

    private CompanyType companyType;
    private String name;
    private LocalDate birthDate;
    private String linesOfBusiness;
    private String email;
    private String phone;
    private Long businessArea;
    private String tradingName;
    private Address address;
    private BankAccount bankAccount;
    private LegalRepresentative legalRepresentative;

    public static JunoDigitalAccountPatchRequest forIndividual(String name, String email, String phone, LocalDate birthDate, Long businessArea,
            String linesOfBusiness, Address address, BankAccount bankAccount) {
        return new JunoDigitalAccountPatchRequest(name, email, phone, birthDate, businessArea, linesOfBusiness, address, bankAccount);
    }

    public static JunoDigitalAccountPatchRequest forCompany(String name, String email, String phone, LocalDate birthDate, Long businessArea,
            String linesOfBusiness, CompanyType companyType, LegalRepresentative legalRepresentative, Address address, BankAccount bankAccount) {
        return new JunoDigitalAccountPatchRequest(name, email, phone, birthDate, businessArea, linesOfBusiness, companyType, legalRepresentative,
                address, bankAccount);
    }

    public JunoDigitalAccountPatchRequest(String name, String email, String phone, LocalDate birthDate, Long businessArea, String linesOfBusiness,
            CompanyType companyType, LegalRepresentative legalRepresentative, Address address, BankAccount bankAccount) {
        this(name, email, phone, birthDate, businessArea, linesOfBusiness, address, bankAccount);
        this.companyType = companyType;
        this.legalRepresentative = legalRepresentative;
    }

    public JunoDigitalAccountPatchRequest(String name, String email, String phone, LocalDate birthDate, Long businessArea, String linesOfBusiness,
            Address address, BankAccount bankAccount) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.birthDate = birthDate;
        this.businessArea = businessArea;
        this.linesOfBusiness = linesOfBusiness;
        this.address = address;
        this.bankAccount = bankAccount;
    }

}
