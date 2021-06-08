package com.ngueno.juno.sdk.resources.transfers.model;

import java.math.BigDecimal;

import com.ngueno.juno.sdk.resources.base.model.TransferType;

public class JunoDefaultBankAccountTransferRequest extends JunoTransferRequest {

    public JunoDefaultBankAccountTransferRequest(BigDecimal amount) {
        super(amount);
    }

    @Override
    public TransferType getTransferType() {
        return TransferType.DEFAULT_BANK_ACCOUNT;
    }
}
