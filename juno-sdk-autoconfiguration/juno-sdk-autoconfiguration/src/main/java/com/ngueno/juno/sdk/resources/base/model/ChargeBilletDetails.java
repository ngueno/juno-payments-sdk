package com.ngueno.juno.sdk.resources.base.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ChargeBilletDetails {

    private String bankAccount;
    private String ourNumber;
    private String barcodeNumber;
    private String portfolio;
}
