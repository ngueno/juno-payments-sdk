package com.ngueno.juno.sdk.resources.digitalaccounts;

import static com.ngueno.juno.sdk.config.JunoApiFormats.API_ISO_DATE_TIME_PATTERN;
import static com.ngueno.juno.sdk.resources.base.model.BankAccountType.CHECKING;
import static com.ngueno.juno.sdk.resources.base.model.CompanyType.MEI;
import static com.ngueno.juno.sdk.resources.base.model.DigitalAccountStatus.AWAITING_DOCUMENTS;
import static com.ngueno.juno.sdk.resources.base.model.DigitalAccountType.PAYMENT;
import static com.ngueno.juno.sdk.resources.base.model.PersonType.F;
import static com.ngueno.juno.sdk.resources.base.model.PersonType.J;
import static com.ngueno.juno.sdk.test.FixtureHelper.ACCOUNT_NUMBER;
import static com.ngueno.juno.sdk.test.FixtureHelper.ACCOUNT_NUMBER_COMPLEMENT;
import static com.ngueno.juno.sdk.test.FixtureHelper.AGENCY_NUMBER;
import static com.ngueno.juno.sdk.test.FixtureHelper.BANK_NUMBER;
import static com.ngueno.juno.sdk.test.FixtureHelper.BIRTH_DATE;
import static com.ngueno.juno.sdk.test.FixtureHelper.BUSINESS_AREA_ID;
import static com.ngueno.juno.sdk.test.FixtureHelper.CNAE;
import static com.ngueno.juno.sdk.test.FixtureHelper.DOCUMENT_CNPJ;
import static com.ngueno.juno.sdk.test.FixtureHelper.DOCUMENT_CPF;
import static com.ngueno.juno.sdk.test.FixtureHelper.EMAIL;
import static com.ngueno.juno.sdk.test.FixtureHelper.ESTABLISHMENT_DATE;
import static com.ngueno.juno.sdk.test.FixtureHelper.LINE_OF_BUSINESS;
import static com.ngueno.juno.sdk.test.FixtureHelper.MONTHLY_INCOME_OR_REVENUE;
import static com.ngueno.juno.sdk.test.FixtureHelper.MOTHER_NAME;
import static com.ngueno.juno.sdk.test.FixtureHelper.NAME;
import static com.ngueno.juno.sdk.test.FixtureHelper.PHONE;
import static java.time.format.DateTimeFormatter.ofPattern;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.util.List;

import javax.annotation.Resource;

import com.ngueno.juno.sdk.resources.base.model.BankAccount;
import com.ngueno.juno.sdk.resources.digitalaccounts.model.DigitalAccountResource;
import com.ngueno.juno.sdk.resources.digitalaccounts.model.JunoDigitalAccountCreateRequest;
import com.ngueno.juno.sdk.resources.digitalaccounts.model.JunoDigitalAccountPatchRequest;
import com.ngueno.juno.sdk.test.AbstractSpringBootTest;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.mockserver.model.ExpectationId;

class JunoDigitalAccountServiceTest extends AbstractSpringBootTest {

    @AfterEach
    void clearMocks() {
        mockServerManager().mockServer().clear((ExpectationId)null);
    }

    @Test
    void findDigitalAccountCompany() {
        mockServerManager().expectDigitalAccountFindCompany();

        DigitalAccountResource resource = service.findDigitalAccount();

        assertDigitalAccount(resource);
        assertCompany(resource);
    }

    @Test
    void findDigitalAccountIndividual() {
        mockServerManager().expectDigitalAccountFindIndividual();

        DigitalAccountResource resource = service.findDigitalAccount();

        assertDigitalAccount(resource);
        assertIndividual(resource);
    }

    @Test
    void createDigitalAccountForIndividual() {
        mockServerManager().expectDigitalAccountCreateIndividual();

        // @formatter:off
        JunoDigitalAccountCreateRequest request = JunoDigitalAccountCreateRequest.forIndividual(
                NAME,
                DOCUMENT_CPF,
                EMAIL,
                PHONE,
                BIRTH_DATE,
                MOTHER_NAME,
                BUSINESS_AREA_ID,
                LINE_OF_BUSINESS,
                MONTHLY_INCOME_OR_REVENUE,
                helper().createAddress(),
                createBankAccount()
        );
        // @formatter:on

        DigitalAccountResource resource = service.createDigitalAccount(request);

        assertDigitalAccountConsideringResourceToken(resource);
        assertIndividual(resource);
    }

    @Test
    void createDigitalAccountForCompany() {
        mockServerManager().expectDigitalAccountCreateCompany();

        // @formatter:off
        JunoDigitalAccountCreateRequest request = JunoDigitalAccountCreateRequest.forCompany(
                NAME,
                DOCUMENT_CNPJ,
                EMAIL,
                PHONE,
                BIRTH_DATE,
                BUSINESS_AREA_ID,
                LINE_OF_BUSINESS,
                MEI,
                helper().createLegalRepresentative(),
                MONTHLY_INCOME_OR_REVENUE,
                CNAE,
                ESTABLISHMENT_DATE,
                List.of(helper().createCompanyMember()), // This is only mandatory if the CompanyType == SA or LTDA
                helper().createAddress(),
                createBankAccount()
        );
        // @formatter:on

        DigitalAccountResource resource = service.createDigitalAccount(request);

        assertDigitalAccountConsideringResourceToken(resource);
        assertCompany(resource);
    }

    @Test
    void updateDigitalAccountCompany() {
        mockServerManager().expectDigitalAccountUpdateCompany();

        // @formatter:off
        JunoDigitalAccountPatchRequest request = JunoDigitalAccountPatchRequest.forCompany(
                NAME,
                EMAIL,
                PHONE,
                BIRTH_DATE,
                BUSINESS_AREA_ID,
                LINE_OF_BUSINESS,
                MEI,
                helper().createLegalRepresentative(),
                helper().createAddress(),
                createBankAccount()
        );
        // @formatter:on

        DigitalAccountResource resource = service.updateDigitalAccount(request);

        assertDigitalAccount(resource);
        assertCompany(resource);
    }

    @Test
    void updateDigitalAccountIndividualIndividual() {
        mockServerManager().expectDigitalAccountUpdateIndividual();

        // @formatter:off
        JunoDigitalAccountPatchRequest request = JunoDigitalAccountPatchRequest.forIndividual(
                NAME,
                EMAIL,
                PHONE,
                BIRTH_DATE,
                BUSINESS_AREA_ID,
                LINE_OF_BUSINESS,
                helper().createAddress(),
                createBankAccount()
        );
        // @formatter:on

        DigitalAccountResource resource = service.updateDigitalAccount(request);

        assertDigitalAccount(resource);
        assertIndividual(resource);
    }

    private BankAccount createBankAccount() {
        return BankAccount.forDigitalAccountCreationOrUpdate(BANK_NUMBER, AGENCY_NUMBER, ACCOUNT_NUMBER, ACCOUNT_NUMBER_COMPLEMENT, CHECKING,
                helper().createBankAccountHolder());
    }

    private void assertIndividual(DigitalAccountResource resource) {
        assertEquals(F, resource.getPersonType());
        assertTrue(resource.isPerson());
        assertFalse(resource.isCompany());
    }

    private void assertCompany(DigitalAccountResource resource) {
        assertEquals(J, resource.getPersonType());
        assertTrue(resource.isCompany());
        assertFalse(resource.isPerson());
    }

    private void assertDigitalAccount(DigitalAccountResource resource) {
        assertDigitalAccount(resource, false);
    }

    private void assertDigitalAccountConsideringResourceToken(DigitalAccountResource resource) {
        assertDigitalAccount(resource, true);
    }

    private void assertDigitalAccount(DigitalAccountResource resource, boolean considerResourceToken) {
        assertEquals("dac_A5BD5878FE083527", resource.getId());
        assertEquals(PAYMENT, resource.getType());
        assertEquals(AWAITING_DOCUMENTS, resource.getStatus());
        assertEquals(LocalDateTime.parse("2021-07-02T23:26:37.648-03:00", ofPattern(API_ISO_DATE_TIME_PATTERN)), resource.getCreatedOn());
        assertEquals(10000284677L, resource.getAccountNumber());
        assertEquals(resource.isCompany() ? "99391775000100" : "56489652064", resource.getDocument());

        if (considerResourceToken) {
            assertEquals("C2C2B71E7AEE09CDC043A80F6FDE5FA8DEC302C0A93E52489EFDC5A9B6D03E68", resource.getResourceToken());
        }
    }

    @Resource
    private JunoDigitalAccountService service;
}
