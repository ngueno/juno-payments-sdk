package com.ngueno.juno.sdk.resources.base.http;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(value = Include.NON_EMPTY)
public interface JunoBaseRequest {
    //
}
