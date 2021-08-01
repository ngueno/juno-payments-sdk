package com.ngueno.juno.sdk.resources.transfers.model;

import java.math.BigDecimal;

import com.ngueno.juno.sdk.resources.base.model.TransferType;

import lombok.ToString;

@ToString(callSuper = true)
public class JunoDefaultBankAccountTransferRequest extends JunoTransferRequest {

    /**
     * Transfer total balance option :)
     */
    public JunoDefaultBankAccountTransferRequest() {
        this(null);
    }

    public JunoDefaultBankAccountTransferRequest(BigDecimal amount) {
        super(amount);
    }

    @Override
    public TransferType getTransferType() {
        return TransferType.DEFAULT_BANK_ACCOUNT;
    }
}
