package com.ngueno.juno.sdk.model.error;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString(callSuper = true)
public class JunoApiIntegrationException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private JunoApiError error;

    public JunoApiIntegrationException(JunoApiError error) {
        this.error = error;
    }

    public JunoApiIntegrationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public JunoApiIntegrationException(String message, Throwable cause) {
        super(message, cause);
    }

    public JunoApiIntegrationException(String message) {
        super(message);
    }

}
