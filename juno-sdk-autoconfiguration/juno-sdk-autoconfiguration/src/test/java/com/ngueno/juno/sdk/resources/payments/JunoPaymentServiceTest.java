package com.ngueno.juno.sdk.resources.payments;

import static com.ngueno.juno.sdk.test.FixtureHelper.PAYMENT_ID;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import javax.annotation.Resource;

import org.junit.jupiter.api.Test;

import com.ngueno.juno.sdk.resources.base.model.PaymentBilling;
import com.ngueno.juno.sdk.resources.base.model.PaymentStatus;
import com.ngueno.juno.sdk.resources.base.model.PaymentType;
import com.ngueno.juno.sdk.resources.payments.model.JunoPaymentCaptureRequest;
import com.ngueno.juno.sdk.resources.payments.model.JunoPaymentCreateRequest;
import com.ngueno.juno.sdk.resources.payments.model.JunoPaymentRefundRequest;
import com.ngueno.juno.sdk.resources.payments.model.PaymentResource;
import com.ngueno.juno.sdk.resources.payments.model.PaymentResources;
import com.ngueno.juno.sdk.test.AbstractSpringBootTest;
import com.ngueno.juno.sdk.test.FixtureHelper;

class JunoPaymentServiceTest extends AbstractSpringBootTest {

    @Test
    void createPayments() {
        PaymentBilling paymentBilling = new PaymentBilling("dummy@email.com", helper().createAddress());
        JunoPaymentCreateRequest request = new JunoPaymentCreateRequest(FixtureHelper.CHARGE_ID, paymentBilling,
                helper().createCreditCardDetails());

        mockServerManager().expectPaymentCreate(request);

        PaymentResources resources = service.createPayments(request);
        assertEquals("3075b373b4efef", resources.getTransactionId());
        assertEquals(1, resources.getInstallments());

        assertPayment(resources);
    }

    @Test
    void refundPayments() {
        JunoPaymentRefundRequest request = new JunoPaymentRefundRequest(PAYMENT_ID);

        mockServerManager().expectPaymentRefund(request);

        PaymentResources resources = service.refundPayments(request);
        assertEquals("3075b373b4efef", resources.getTransactionId());
        assertEquals(1, resources.getInstallments());

        assertEquals(1, resources.getPayments().size());
        PaymentResource resource = resources.getPayments().get(0);
        assertEquals("pay_2CDC0F24A6753435D9A4DBEC51C79D69", resource.getId());
        assertEquals("chr_B98DF63E28D0A31FE6F0EB29414BE49D", resource.getChargeId());
        assertEquals(parseDate("2021-03-22"), resource.getDate());
        assertEquals(parseDate("2021-04-23"), resource.getReleaseDate());
        assertBigDecimal("900.00", resource.getAmount());
        assertBigDecimal("34.61", resource.getFee());
        assertEquals(PaymentType.CREDIT_CARD, resource.getType());
        assertEquals(PaymentStatus.CONFIRMED, resource.getStatus());
        assertEquals("3075b373b4efef", resource.getTransactionId());
        assertNull(resource.getFailReason());

        assertEquals(1, resources.getRefunds().size());
        resource = resources.getRefunds().get(0);
        assertEquals("pay_4F1ED802EC7E5CDA1985C4A892725E94", resource.getId());
        assertEquals("chr_B98DF63E28D0A31FE6F0EB29414BE49D", resource.getChargeId());
        assertEquals(parseDate("2021-04-23"), resource.getReleaseDate());
        assertEquals(parseDate("2021-03-22"), resource.getPaybackDate());
        assertBigDecimal("1000.00", resource.getAmount());
        assertBigDecimal("38.40", resource.getFee());
        assertEquals(PaymentStatus.PARTIALLY_REFUNDED, resource.getStatus());
        assertEquals("3075b373b4efef", resource.getTransactionId());
        assertNull(resource.getDate());
        assertNull(resource.getType());
        assertNull(resource.getFailReason());
    }

    @Test
    void capturePayments() {
        JunoPaymentCaptureRequest request = new JunoPaymentCaptureRequest(PAYMENT_ID);

        mockServerManager().expectPaymentCapture(request);

        PaymentResources resources = service.capturePayments(request);
        assertEquals("3075b373b4efef", resources.getTransactionId());
        assertEquals(1, resources.getInstallments());

        assertPayment(resources);
    }

    private void assertPayment(PaymentResources resources) {
        assertEquals(1, resources.getPayments().size());
        PaymentResource resource = resources.getPayments().get(0);
        assertEquals("pay_4F1ED802EC7E5CDA1985C4A892725E94", resource.getId());
        assertEquals("chr_B98DF63E28D0A31FE6F0EB29414BE49D", resource.getChargeId());
        assertEquals(parseDate("2021-03-22"), resource.getDate());
        assertEquals(parseDate("2021-04-23"), resource.getReleaseDate());
        assertBigDecimal("1000.00", resource.getAmount());
        assertBigDecimal("38.40", resource.getFee());
        assertEquals(PaymentType.CREDIT_CARD, resource.getType());
        assertEquals(PaymentStatus.CONFIRMED, resource.getStatus());
        assertNull(resource.getFailReason());
    }

    @Resource
    private JunoPaymentService service;
}
