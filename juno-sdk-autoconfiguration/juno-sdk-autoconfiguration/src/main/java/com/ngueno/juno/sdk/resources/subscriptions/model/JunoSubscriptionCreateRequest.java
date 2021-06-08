package com.ngueno.juno.sdk.resources.subscriptions.model;

import java.util.ArrayList;
import java.util.List;

import com.ngueno.juno.sdk.resources.base.http.JunoBaseRequest;
import com.ngueno.juno.sdk.resources.base.model.ChargeBilling;
import com.ngueno.juno.sdk.resources.base.model.CreditCardDetails;
import com.ngueno.juno.sdk.resources.base.model.Split;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@RequiredArgsConstructor
public class JunoSubscriptionCreateRequest implements JunoBaseRequest {

    private final Integer dueDay;
    private final String planId;
    private final String chargeDescription;
    private final CreditCardDetails creditCardDetails;
    private final ChargeBilling billing;
    private List<Split> split = new ArrayList<>();
}
