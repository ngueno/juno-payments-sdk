package com.ngueno.juno.sdk.resources.billpayments;

import com.ngueno.juno.sdk.resources.base.http.JunoBaseService;
import com.ngueno.juno.sdk.resources.billpayments.model.BillPaymentResource;
import com.ngueno.juno.sdk.resources.billpayments.model.JunoBillPaymentPayBillRequest;

import org.springframework.stereotype.Component;

@Component
public class JunoBillPaymentService extends JunoBaseService {

    public BillPaymentResource payBill(JunoBillPaymentPayBillRequest request) {
        return payBill(getResourceToken(), request);
    }

    public BillPaymentResource payBill(String resourceToken, JunoBillPaymentPayBillRequest request) {
        return http().post("/bill-payments", resourceToken, request, BillPaymentResource.class);
    }
}
