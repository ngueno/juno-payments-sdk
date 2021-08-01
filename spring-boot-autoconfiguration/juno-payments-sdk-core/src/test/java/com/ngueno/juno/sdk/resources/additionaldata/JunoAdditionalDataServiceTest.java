package com.ngueno.juno.sdk.resources.additionaldata;

import static org.junit.jupiter.api.Assertions.assertEquals;

import javax.annotation.Resource;

import com.ngueno.juno.sdk.resources.additionaldata.model.BankResource;
import com.ngueno.juno.sdk.resources.additionaldata.model.BankResources;
import com.ngueno.juno.sdk.resources.additionaldata.model.BusinessAreaResource;
import com.ngueno.juno.sdk.resources.additionaldata.model.BusinessAreaResources;
import com.ngueno.juno.sdk.resources.additionaldata.model.CompanyTypeResources;
import com.ngueno.juno.sdk.test.AbstractSpringBootTest;

import org.junit.jupiter.api.Test;

class JunoAdditionalDataServiceTest extends AbstractSpringBootTest {

    @Test
    void listBanks() {
        mockServerManager().expectAdditionalDataBanksList();

        BankResources resources = service.listBanks();

        assertEquals(2, resources.getResources().size());
        assertBank("001", "BCO DO BRASIL S.A.", resources.getResources().get(0));
        assertBank("003", "BCO DA AMAZONIA S.A.", resources.getResources().get(1));
    }

    @Test
    void listCompanyTypes() {
        mockServerManager().expectAdditionalDataCompanyTypesList();

        CompanyTypeResources resources = service.listCompanyTypes();

        assertEquals(6, resources.getCompanyTypes().size());
        assertEquals("MEI", resources.getCompanyTypes().get(0));
        assertEquals("EI", resources.getCompanyTypes().get(1));
        assertEquals("EIRELI", resources.getCompanyTypes().get(2));
        assertEquals("LTDA", resources.getCompanyTypes().get(3));
        assertEquals("SA", resources.getCompanyTypes().get(4));
        assertEquals("INSTITUTION_NGO_ASSOCIATION", resources.getCompanyTypes().get(5));
    }

    @Test
    void listBusinessAreas() {
        mockServerManager().expectAdditionalDataBusinessAreasList();

        BusinessAreaResources resources = service.listBusinessAreas();

        assertEquals(2, resources.getResources().size());
        assertBusinessArea(1000L, "Produtos", "Acessorios automotivos", resources.getResources().get(0));
        assertBusinessArea(1001L, "Produtos", "Agronomia e agricultura", resources.getResources().get(1));
    }

    private void assertBank(String expectedNumber, String expectedName, BankResource resource) {
        assertEquals(expectedNumber, resource.getNumber());
        assertEquals(expectedName, resource.getName());
    }

    private void assertBusinessArea(Long expectedCode, String expectedActivity, String expectCategory, BusinessAreaResource resource) {
        assertEquals(expectedCode, resource.getCode());
        assertEquals(expectedActivity, resource.getActivity());
        assertEquals(expectCategory, resource.getCategory());
    }

    @Resource
    private JunoAdditionalDataService service;
}
