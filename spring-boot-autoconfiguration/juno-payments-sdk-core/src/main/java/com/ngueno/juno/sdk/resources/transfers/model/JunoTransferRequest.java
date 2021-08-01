package com.ngueno.juno.sdk.resources.transfers.model;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ngueno.juno.sdk.resources.base.http.JunoBaseRequest;
import com.ngueno.juno.sdk.resources.base.model.BankAccount;
import com.ngueno.juno.sdk.resources.base.model.TransferType;

import lombok.Getter;
import lombok.ToString;

// TODO: Build a factory
@Getter
@ToString
public abstract class JunoTransferRequest implements JunoBaseRequest {

    protected String name;
    protected String document;
    protected BigDecimal amount;

    protected BankAccount bankAccount;

    @JsonProperty(value = "type")
    public abstract TransferType getTransferType();

    protected JunoTransferRequest(String name, String document, BigDecimal amount, BankAccount bankAccount) {
        this(name, document, amount);
        this.bankAccount = bankAccount;
    }

    protected JunoTransferRequest(String name, String document, BigDecimal amount) {
        this(amount);
        this.name = name;
        this.document = document;
    }

    protected JunoTransferRequest(BigDecimal amount) {
        this.amount = amount;
    }
}
