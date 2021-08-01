package com.ngueno.juno.sdk.resources.credentials;

import org.springframework.stereotype.Component;

import com.ngueno.juno.sdk.resources.base.http.JunoBaseService;
import com.ngueno.juno.sdk.resources.credentials.model.JunoPublicKey;

@Component
public class JunoCredentialsService extends JunoBaseService {

    public JunoPublicKey getPublicKey() {
        return getPublicKey(getResourceToken());
    }

    public JunoPublicKey getPublicKey(String resourceToken) {
        return new JunoPublicKey(http().get("/credentials/public-key", resourceToken, String.class));
    }
}
