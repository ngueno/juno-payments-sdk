package com.ngueno.juno.sdk.resources.credentials;

import com.ngueno.juno.sdk.resources.base.http.JunoBaseService;
import com.ngueno.juno.sdk.resources.credentials.model.JunoPublicKey;

import org.springframework.stereotype.Component;

@Component
public class JunoCredentialsService extends JunoBaseService {

    public JunoPublicKey getPublicKey() {
        return getPublicKey(getResourceToken());
    }

    public JunoPublicKey getPublicKey(String resourceToken) {
        return new JunoPublicKey(http().get("/credentials/public-key", resourceToken, String.class));
    }
}
