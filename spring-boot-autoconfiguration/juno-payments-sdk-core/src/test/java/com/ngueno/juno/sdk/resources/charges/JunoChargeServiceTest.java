package com.ngueno.juno.sdk.resources.charges;

import static com.ngueno.juno.sdk.test.FixtureHelper.CHARGE_ID;
import static com.ngueno.juno.sdk.test.FixtureHelper.DESCRIPTION;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import javax.annotation.Resource;

import com.ngueno.juno.sdk.model.error.JunoApiIntegrationException;
import com.ngueno.juno.sdk.resources.base.model.Charge;
import com.ngueno.juno.sdk.resources.base.model.ChargeBilling;
import com.ngueno.juno.sdk.resources.base.model.ChargeStatus;
import com.ngueno.juno.sdk.resources.charges.model.ChargeResource;
import com.ngueno.juno.sdk.resources.charges.model.ChargeResources;
import com.ngueno.juno.sdk.resources.charges.model.JunoChargeCreateRequest;
import com.ngueno.juno.sdk.resources.charges.model.JunoChargeListRequest;
import com.ngueno.juno.sdk.resources.charges.model.JunoChargeSplitUpdateRequest;
import com.ngueno.juno.sdk.test.AbstractSpringBootTest;

import org.junit.jupiter.api.Test;

class JunoChargeServiceTest extends AbstractSpringBootTest {

    @Test
    void createCharges() {
        Charge charge = new Charge(DESCRIPTION);
        ChargeBilling chargeBilling = helper().createChargeBilling();
        JunoChargeCreateRequest request = new JunoChargeCreateRequest(charge, chargeBilling);

        mockServerManager().expectChargeCreate(request);

        ChargeResources resources = service.createCharges(request);

        assertEquals(1, resources.getResources().size());
        assertCharge(resources.getResources().get(0));
    }

    @Test
    void listCharges() {
        mockServerManager().expectChargeList();

        ChargeResources resources = service.listCharges(new JunoChargeListRequest());

        assertEquals(1, resources.getResources().size());
        assertCharge(resources.getResources().get(0));
        assertHateoasListLinks(resources);
    }

    @Test
    void listChargesWithHateoasLink() {
        mockServerManager().expectChargeList(
                "lastObjectId=chr_4F3A23C9D69DE4D916BD249224769F9C&lastValue=chr_4F3A23C9D69DE4D916BD249224769F9C&page=3&pageSize=20");

        ChargeResources resources = service.listCharges(
                "https://dummy-env/api-integration/charges?lastObjectId=chr_4F3A23C9D69DE4D916BD249224769F9C&lastValue=chr_4F3A23C9D69DE4D916BD249224769F9C&page=3&pageSize=20");

        assertEquals(1, resources.getResources().size());
        assertCharge(resources.getResources().get(0));
        assertHateoasListLinks(resources);
    }

    @Test
    void listChargesWithBrokenHateoasLink() {
        try {
            service.listCharges("somebrokenLink.br.com.bl.xpto");
            fail();
        } catch (JunoApiIntegrationException e) {
            assertEquals("Failed to parse given link", e.getMessage());
        }
    }

    @Test
    void findCharge() {
        mockServerManager().expectChargeFind(CHARGE_ID);

        ChargeResource resource = service.findCharge(CHARGE_ID);

        assertCharge(resource);
    }

    @Test
    void cancelCharge() {
        mockServerManager().expectChargeCancelation(CHARGE_ID);

        service.cancelCharge(CHARGE_ID);

        mockServerManager().verifyChargeCancelation(CHARGE_ID);
    }

    @Test
    void updateSplit() {
        JunoChargeSplitUpdateRequest request = new JunoChargeSplitUpdateRequest(CHARGE_ID);

        mockServerManager().expectChargeUpdateSplit(request);

        service.updateSplit(request);

        mockServerManager().verifyChargeUpdateSplit(request);
    }

    private void assertCharge(ChargeResource resource) {
        assertEquals("chr_575A9ADE6C14BFD4330B28BA60253850", resource.getId());
        assertEquals(136399592, resource.getCode());
        assertEquals("REF1", resource.getReference());
        assertEquals(parseDate("2021-05-27"), resource.getDueDate());
        assertEquals("https://dummy-env/checkout/4FA21093ACBF3F9DDC0084987C5208DEB8BCD432C817E8D6",
                resource.getCheckoutUrl());
        assertBigDecimal("1000.00", resource.getAmount());
        assertEquals(ChargeStatus.ACTIVE, resource.getStatus());
        assertEquals("https://dummy-env/api-integration/charges/chr_575A9ADE6C14BFD4330B28BA60253850",
                resource.getLinks("self").getHref());
    }

    private void assertHateoasListLinks(ChargeResources resources) {
        assertEquals(
                "https://dummy-env/api-integration/charges?lastObjectId=chr_F7B72712A8CA92194B6C30E1671C422B&lastValue=chr_F7B72712A8CA92194B6C30E1671C422B&page=2&pageSize=20",
                resources.getLinks("self").getHref());
        assertEquals(
                "https://dummy-env/api-integration/charges?lastObjectId=chr_4F3A23C9D69DE4D916BD249224769F9C&lastValue=chr_4F3A23C9D69DE4D916BD249224769F9C&page=3&pageSize=20",
                resources.getLinks("next").getHref());
        assertEquals(
                "https://dummy-env/api-integration/charges?firstObjectId=chr_F7B72712A8CA92194B6C30E1671C422B&firstValue=chr_F7B72712A8CA92194B6C30E1671C422B&page=1&pageSize=20",
                resources.getLinks("previous").getHref());
    }

    @Resource
    private JunoChargeService service;
}