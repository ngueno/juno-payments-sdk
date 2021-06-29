package com.ngueno.juno.sdk.resources.transfers;

import org.springframework.stereotype.Component;

import com.ngueno.juno.sdk.resources.base.http.JunoBaseService;
import com.ngueno.juno.sdk.resources.transfers.model.JunoTransferRequest;
import com.ngueno.juno.sdk.resources.transfers.model.TransferResource;

@Component
public class JunoTransferService extends JunoBaseService {

    public TransferResource createTransfer(JunoTransferRequest request) {
        return createTransfer(getResourceToken(), request);
    }

    public TransferResource createTransfer(String resourceToken, JunoTransferRequest request) {
        return http().post("/transfers", resourceToken, request, TransferResource.class);
    }
}
