package com.ngueno.juno.sdk.resources.base.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ChargePix {

    private String id;
    private String qrcodeInBase64;
    private String imageInBase64;
}
