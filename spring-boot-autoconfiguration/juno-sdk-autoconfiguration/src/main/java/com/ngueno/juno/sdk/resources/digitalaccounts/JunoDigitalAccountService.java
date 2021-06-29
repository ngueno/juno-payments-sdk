package com.ngueno.juno.sdk.resources.digitalaccounts;

import com.ngueno.juno.sdk.resources.base.http.JunoBaseService;
import com.ngueno.juno.sdk.resources.digitalaccounts.model.DigitalAccountResource;
import com.ngueno.juno.sdk.resources.digitalaccounts.model.JunoDigitalAccountCreateRequest;
import com.ngueno.juno.sdk.resources.digitalaccounts.model.JunoDigitalAccountPatchRequest;

import org.springframework.stereotype.Component;

@Component
public class JunoDigitalAccountService extends JunoBaseService {

    private static final String DIGITAL_ACCOUNTS = "/digital-accounts";

    public DigitalAccountResource createDigitalAccount(JunoDigitalAccountCreateRequest request) {
        return createDigitalAccount(getResourceToken(), request);
    }

    public DigitalAccountResource createDigitalAccount(String resourceToken, JunoDigitalAccountCreateRequest request) {
        return http().post(DIGITAL_ACCOUNTS, resourceToken, request, DigitalAccountResource.class);
    }

    public DigitalAccountResource updateDigitalAccount(JunoDigitalAccountPatchRequest request) {
        return updateDigitalAccount(getResourceToken(), request);
    }

    public DigitalAccountResource updateDigitalAccount(String resourceToken, JunoDigitalAccountPatchRequest request) {
        return http().patch(DIGITAL_ACCOUNTS, resourceToken, request, DigitalAccountResource.class);
    }

    public DigitalAccountResource findDigitalAccount() {
        return findDigitalAccount(getResourceToken());
    }

    public DigitalAccountResource findDigitalAccount(String resourceToken) {
        return http().get(DIGITAL_ACCOUNTS, resourceToken, DigitalAccountResource.class);
    }
}
