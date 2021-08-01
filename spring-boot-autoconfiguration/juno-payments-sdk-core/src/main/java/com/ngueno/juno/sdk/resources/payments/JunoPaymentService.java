package com.ngueno.juno.sdk.resources.payments;

import com.ngueno.juno.sdk.resources.base.http.JunoBaseService;
import com.ngueno.juno.sdk.resources.payments.model.JunoPaymentCaptureRequest;
import com.ngueno.juno.sdk.resources.payments.model.JunoPaymentCreateRequest;
import com.ngueno.juno.sdk.resources.payments.model.JunoPaymentRefundRequest;
import com.ngueno.juno.sdk.resources.payments.model.PaymentResources;

import org.springframework.stereotype.Component;

@Component
public class JunoPaymentService extends JunoBaseService {

    private static final String PAYMENTS = "/payments";
    private static final String PAYMENTS_ID = "/payments/{id}";

    public PaymentResources createPayments(JunoPaymentCreateRequest request) {
        return createPayments(getResourceToken(), request);
    }

    public PaymentResources createPayments(String resourceToken, JunoPaymentCreateRequest request) {
        return http().post(PAYMENTS, resourceToken, request, PaymentResources.class);
    }

    public PaymentResources refundPayments(JunoPaymentRefundRequest request) {
        return refundPayments(getResourceToken(), request);
    }

    public PaymentResources refundPayments(String resourceToken, JunoPaymentRefundRequest request) {
        return http().post(expandId(PAYMENTS_ID + "/refunds", request.getPaymentId()), resourceToken, request,
                PaymentResources.class);
    }

    public PaymentResources capturePayments(JunoPaymentCaptureRequest request) {
        return capturePayments(getResourceToken(), request);
    }

    public PaymentResources capturePayments(String resourceToken, JunoPaymentCaptureRequest request) {
        return http().post(expandId(PAYMENTS_ID + "/capture", request.getPaymentId()), resourceToken, request,
                PaymentResources.class);
    }
}
