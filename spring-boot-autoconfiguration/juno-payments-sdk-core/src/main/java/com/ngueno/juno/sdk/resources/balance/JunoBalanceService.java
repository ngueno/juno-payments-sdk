package com.ngueno.juno.sdk.resources.balance;

import com.ngueno.juno.sdk.resources.balance.model.BalanceResource;
import com.ngueno.juno.sdk.resources.base.http.JunoBaseService;

import org.springframework.stereotype.Service;

@Service
public class JunoBalanceService extends JunoBaseService {

    public BalanceResource getBalance() {
        return getBalance(getResourceToken());
    }

    public BalanceResource getBalance(String resourceToken) {
        return http().get("/balance", resourceToken, BalanceResource.class);
    }
}
