package com.ngueno.juno.sdk.resources.base.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BankAccount extends JunoBaseModel {

    private String bankNumber;
    private String agencyNumber;
    private String accountNumber;
    private String accountComplementNumber;
    private BankAccountType accountType;

    private String ispb;

    private BankAccountHolder accountHolder;

    public static BankAccount forDigitalAccountCreationOrUpdate(String bankNumber, String agencyNumber, String accountNumber, String accountComplementNumber,
            BankAccountType accountType, BankAccountHolder accountHolder) {
        return new BankAccount(bankNumber, agencyNumber, accountNumber, accountComplementNumber, accountType, accountHolder);
    }

    public static BankAccount forP2pTransfer(String digitalAccountId) {
        return new BankAccount(digitalAccountId);
    }

    public static BankAccount forBankAccountTranfer(String bankNumber, String agencyNumber, String accountNumber, String accountComplementNumber,
            BankAccountType accountType) {
        return new BankAccount(bankNumber, agencyNumber, accountNumber, accountComplementNumber, accountType);
    }

    public static BankAccount forPixTransfer(String bankNumber, String ispb, String agencyNumber, String accountNumber,
            String accountComplementNumber, BankAccountType accountType) {
        return new BankAccount(bankNumber, ispb, agencyNumber, accountNumber, accountComplementNumber, accountType);
    }

    private BankAccount(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    private BankAccount(String bankNumber, String ispb, String agencyNumber, String accountNumber, String accountComplementNumber,
            BankAccountType accountType) {
        this(bankNumber, agencyNumber, accountNumber, accountComplementNumber, accountType);
        this.ispb = ispb;
    }

    public BankAccount(String bankNumber, String agencyNumber, String accountNumber, String accountComplementNumber, BankAccountType accountType) {
        this(bankNumber, agencyNumber, accountNumber, accountComplementNumber, accountType, null);
    }

    public BankAccount(String bankNumber, String agencyNumber, String accountNumber, String accountComplementNumber, BankAccountType accountType,
            BankAccountHolder accountHolder) {
        this.bankNumber = bankNumber;
        this.agencyNumber = agencyNumber;
        this.accountNumber = accountNumber;
        this.accountComplementNumber = accountComplementNumber;
        this.accountType = accountType;
        this.accountHolder = accountHolder;
    }

}
