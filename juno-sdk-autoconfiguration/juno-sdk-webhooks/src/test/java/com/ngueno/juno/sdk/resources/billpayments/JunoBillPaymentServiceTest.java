package com.ngueno.juno.sdk.resources.billpayments;

import static org.junit.jupiter.api.Assertions.assertEquals;

import javax.annotation.Resource;

import org.junit.jupiter.api.Test;

import com.ngueno.juno.sdk.resources.base.model.BillPaymentStatus;
import com.ngueno.juno.sdk.resources.base.model.BillPaymentType;
import com.ngueno.juno.sdk.resources.billpayments.model.BillPaymentResource;
import com.ngueno.juno.sdk.resources.billpayments.model.JunoBillPaymentPayBillRequest;
import com.ngueno.juno.sdk.test.AbstractServiceTest;

class JunoBillPaymentServiceTest extends AbstractServiceTest {

    @Test
    void payBill() {
        JunoBillPaymentPayBillRequest request = new JunoBillPaymentPayBillRequest(
                "826600000002941601092021104013409685480320219193", "Testing payment", "74309209009",
                parseDate("2021-04-01"), parseDate("2021-03-25"), bd("1000.00"), bd("1000.00"));

        mockServerManager().expectBillPayment(request);

        BillPaymentResource resource = service.payBill(request);

        assertEquals("bil_EF68A889FB82438A", resource.getId());
        assertEquals("826600000002941601092021104013409685480320219193", resource.getNumericalBarCode());
        assertBigDecimal("94.16", resource.getBillAmount());
        assertEquals(parseDate("2021-04-01"), resource.getDueDate());
        assertEquals(BillPaymentType.UTILITY, resource.getBillType());
        assertEquals("dac_BF7FCE967887DCF4", resource.getDigitalAccountId());
        assertBigDecimal("1000.00", resource.getPaidAmount());
        assertEquals(parseDate("2021-03-25"), resource.getPaymentDate());
        assertEquals("Testing payment", resource.getPaymentDescription());
        assertEquals("74309209009", resource.getBeneficiaryDocument());
        assertEquals(BillPaymentStatus.SCHEDULED, resource.getStatus());
        assertEquals(parseDateTime("2021-03-22 22:18:03"), resource.getCreatedOn());
    }

    @Resource
    private JunoBillPaymentService service;
}
