package com.ngueno.juno.sdk.resources.transfers.model;

import java.math.BigDecimal;

import com.ngueno.juno.sdk.resources.base.model.BankAccount;
import com.ngueno.juno.sdk.resources.base.model.TransferType;

public class JunoP2pTransferRequest extends JunoTransferRequest {

    public JunoP2pTransferRequest(String name, String document, BigDecimal amount, BankAccount bankAccount) {
        super(name, document, amount, bankAccount);
    }

    @Override
    public TransferType getTransferType() {
        return TransferType.P2P;
    }
}
