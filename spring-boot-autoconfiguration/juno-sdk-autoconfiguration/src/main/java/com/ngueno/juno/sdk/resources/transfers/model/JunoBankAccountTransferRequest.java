package com.ngueno.juno.sdk.resources.transfers.model;

import java.math.BigDecimal;

import com.ngueno.juno.sdk.resources.base.model.BankAccount;
import com.ngueno.juno.sdk.resources.base.model.TransferType;

import lombok.ToString;

@ToString(callSuper = true)
public class JunoBankAccountTransferRequest extends JunoTransferRequest {

    public JunoBankAccountTransferRequest(String name, String document, BigDecimal amount, BankAccount bankAccount) {
        super(name, document, amount, bankAccount);
    }

    @Override
    public TransferType getTransferType() {
        return TransferType.BANK_ACCOUNT;
    }
}
