package com.ngueno.juno.sdk.resources.charges.model;

import java.util.ArrayList;
import java.util.List;

import com.ngueno.juno.sdk.resources.base.http.JunoBaseRequest;
import com.ngueno.juno.sdk.resources.base.model.Split;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@RequiredArgsConstructor
public class JunoChargeSplitUpdateRequest implements JunoBaseRequest {

    private final String chargeId;
    private List<Split> split = new ArrayList<>();
}
