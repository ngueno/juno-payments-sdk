package com.ngueno.juno.sdk.resources.balance.model;

import java.math.BigDecimal;

import com.ngueno.juno.sdk.resources.base.resource.JunoBaseResource;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class BalanceResource extends JunoBaseResource {

    private BigDecimal balance;
    private BigDecimal withheldBalance;
    private BigDecimal transferableBalance;
}
