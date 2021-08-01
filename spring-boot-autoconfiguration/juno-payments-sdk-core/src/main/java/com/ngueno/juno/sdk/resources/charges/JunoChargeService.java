package com.ngueno.juno.sdk.resources.charges;

import com.ngueno.juno.sdk.resources.base.http.JunoBaseService;
import com.ngueno.juno.sdk.resources.charges.model.ChargeResource;
import com.ngueno.juno.sdk.resources.charges.model.ChargeResources;
import com.ngueno.juno.sdk.resources.charges.model.JunoChargeCreateRequest;
import com.ngueno.juno.sdk.resources.charges.model.JunoChargeListRequest;
import com.ngueno.juno.sdk.resources.charges.model.JunoChargeSplitUpdateRequest;
import com.ngueno.juno.sdk.utils.HateoasDataParser;

import org.springframework.stereotype.Component;

@Component
public class JunoChargeService extends JunoBaseService {

    private static final String CHARGES = "/charges";
    private static final String CHARGES_ID = CHARGES + "/{id}";

    public ChargeResources createCharges(JunoChargeCreateRequest request) {
        return createCharges(getResourceToken(), request);
    }

    public ChargeResources createCharges(String resourceToken, JunoChargeCreateRequest request) {
        return http().post(CHARGES, resourceToken, request, ChargeResources.class);
    }

    public ChargeResources listCharges(JunoChargeListRequest request) {
        return listCharges(getResourceToken(), request);
    }

    public ChargeResources listCharges(String resourceToken, JunoChargeListRequest request) {
        return http().get(CHARGES, resourceToken, ChargeResources.class, request.toQueryParamMap());
    }

    public ChargeResources listCharges(String hateoasLink) {
        return listCharges(getResourceToken(), hateoasLink);
    }

    public ChargeResources listCharges(String resourceToken, String hateoasLink) {
        return http().get(CHARGES, resourceToken, ChargeResources.class,
                new HateoasDataParser(hateoasLink).getParams());
    }

    public ChargeResource findCharge(String chargeId) {
        return findCharge(getResourceToken(), chargeId);
    }

    public ChargeResource findCharge(String resourceToken, String chargeId) {
        return http().get(expandId(CHARGES_ID, chargeId), resourceToken, ChargeResource.class);
    }

    public void cancelCharge(String chargeId) {
        cancelCharge(getResourceToken(), chargeId);
    }

    public void cancelCharge(String resourceToken, String chargeId) {
        http().put(expandId(CHARGES_ID + "/cancelation", chargeId), resourceToken, Void.class);
    }

    public void updateSplit(JunoChargeSplitUpdateRequest request) {
        updateSplit(getResourceToken(), request);
    }

    public void updateSplit(String resourceToken, JunoChargeSplitUpdateRequest request) {
        http().put(expandId(CHARGES_ID + "/split", request.getChargeId()), resourceToken, request, Void.class);
    }

}
