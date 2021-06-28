package com.ngueno.juno.sdk.resources.additionaldata;

import com.ngueno.juno.sdk.resources.additionaldata.model.BankResources;
import com.ngueno.juno.sdk.resources.additionaldata.model.BusinessAreaResources;
import com.ngueno.juno.sdk.resources.additionaldata.model.CompanyTypeResources;
import com.ngueno.juno.sdk.resources.base.http.JunoBaseService;

import org.springframework.stereotype.Service;

@Service
public class JunoAdditionalDataService extends JunoBaseService {

    public BankResources listBanks() {
        return http().get("/data/banks", BankResources.class);
    }

    public CompanyTypeResources listCompanyTypes() {
        return http().get("/data/company-types", CompanyTypeResources.class);
    }

    public BusinessAreaResources listBusinessAreas() {
        return http().get("/data/business-areas", BusinessAreaResources.class);
    }
}
