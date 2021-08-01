package com.ngueno.juno.sdk.model.error;

import java.io.Serializable;
import java.util.List;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class JunoApiError implements Serializable {

    private static final long serialVersionUID = 1L;

    private String timestamp;
    private Integer status;
    private String error;
    private List<ErrorDetail> details;
    private String path;

    @Getter
    @ToString
    public static class ErrorDetail implements Serializable {

        private static final long serialVersionUID = 1L;

        private String field;
        private String message;
        private String errorCode;
    }
}
