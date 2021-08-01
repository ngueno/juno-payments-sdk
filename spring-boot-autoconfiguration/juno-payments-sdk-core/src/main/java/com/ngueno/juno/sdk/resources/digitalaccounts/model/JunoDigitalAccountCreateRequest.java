package com.ngueno.juno.sdk.resources.digitalaccounts.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.ngueno.juno.sdk.config.JunoApiFormats;
import com.ngueno.juno.sdk.resources.base.http.JunoBaseRequest;
import com.ngueno.juno.sdk.resources.base.model.Address;
import com.ngueno.juno.sdk.resources.base.model.BankAccount;
import com.ngueno.juno.sdk.resources.base.model.CompanyMember;
import com.ngueno.juno.sdk.resources.base.model.CompanyType;
import com.ngueno.juno.sdk.resources.base.model.DigitalAccountType;
import com.ngueno.juno.sdk.resources.base.model.LegalRepresentative;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class JunoDigitalAccountCreateRequest implements JunoBaseRequest {

    private final DigitalAccountType type = DigitalAccountType.PAYMENT;
    private final String name;
    private final String document;
    private final String email;
    @JsonFormat(shape = Shape.STRING, pattern = JunoApiFormats.API_DATE_PATTERN)
    private LocalDate birthDate;
    private final String phone;
    private String motherName;
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
    @JsonFormat(shape = Shape.STRING, pattern = JunoApiFormats.API_DATE_PATTERN)
    private LocalDate establishmentDate;
    private List<CompanyMember> companyMembers;

    public static JunoDigitalAccountCreateRequest forIndividual(String name, String document, String email, String phone, LocalDate birthDate,
            String motherName, Long businessArea, String linesOfBusiness, BigDecimal monthlyIncomeOrRevenue, Address address,
            BankAccount bankAccount) {
        return new JunoDigitalAccountCreateRequest(name, document, email, phone, birthDate, motherName, businessArea, linesOfBusiness, address,
                bankAccount, monthlyIncomeOrRevenue);
    }

    public static JunoDigitalAccountCreateRequest forCompany(String name, String document, String email, String phone, LocalDate birthDate,
            Long businessArea, String linesOfBusiness, CompanyType companyType, LegalRepresentative legalRepresentative,
            BigDecimal monthlyIncomeOrRevenue, String cnae, LocalDate establishmentDate, List<CompanyMember> companyMembers, Address address,
            BankAccount bankAccount) {
        return new JunoDigitalAccountCreateRequest(name, document, email, phone, birthDate, businessArea, linesOfBusiness, companyType,
                legalRepresentative, address, bankAccount, monthlyIncomeOrRevenue, cnae, establishmentDate, companyMembers);
    }

    private JunoDigitalAccountCreateRequest(String name, String document, String email, String phone, LocalDate birthDate, String motherName,
            Long businessArea, String linesOfBusiness, Address address, BankAccount bankAccount, BigDecimal monthlyIncomeOrRevenue) {
        this(name, document, email, phone, birthDate, businessArea, linesOfBusiness, address, bankAccount, monthlyIncomeOrRevenue);
        this.motherName = motherName;
    }

    public JunoDigitalAccountCreateRequest(String name, String document, String email, String phone, LocalDate birthDate, Long businessArea,
            String linesOfBusiness, CompanyType companyType, LegalRepresentative legalRepresentative, Address address, BankAccount bankAccount,
            BigDecimal monthlyIncomeOrRevenue, String cnae, LocalDate establishmentDate, List<CompanyMember> companyMembers) {
        this(name, document, email, phone, birthDate, businessArea, linesOfBusiness, address, bankAccount, monthlyIncomeOrRevenue);
        this.companyType = companyType;
        this.legalRepresentative = legalRepresentative;
        this.cnae = cnae;
        this.establishmentDate = establishmentDate;
        this.companyMembers = companyMembers;
    }

    private JunoDigitalAccountCreateRequest(String name, String document, String email, String phone, LocalDate birthDate, Long businessArea,
            String linesOfBusiness, Address address, BankAccount bankAccount, BigDecimal monthlyIncomeOrRevenue) {
        this.name = name;
        this.document = document;
        this.email = email;
        this.phone = phone;
        this.birthDate = birthDate;
        this.businessArea = businessArea;
        this.linesOfBusiness = linesOfBusiness;
        this.address = address;
        this.bankAccount = bankAccount;
        this.monthlyIncomeOrRevenue = monthlyIncomeOrRevenue;
    }

}
