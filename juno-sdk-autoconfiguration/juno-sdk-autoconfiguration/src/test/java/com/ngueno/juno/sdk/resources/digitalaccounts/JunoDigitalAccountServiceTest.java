package com.ngueno.juno.sdk.resources.digitalaccounts;

import static com.ngueno.juno.sdk.config.JunoApiFormats.API_ISO_DATE_TIME_PATTERN;
import static com.ngueno.juno.sdk.resources.base.model.DigitalAccountStatus.VERIFIED;
import static com.ngueno.juno.sdk.resources.base.model.DigitalAccountType.PAYMENT;
import static com.ngueno.juno.sdk.resources.base.model.PersonType.F;
import static com.ngueno.juno.sdk.resources.base.model.PersonType.J;
import static java.time.format.DateTimeFormatter.ofPattern;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;

import javax.annotation.Resource;

import com.ngueno.juno.sdk.resources.digitalaccounts.model.DigitalAccountResource;
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
        assertEquals(J, resource.getPersonType());
        assertTrue(resource.isCompany());

        assertFalse(resource.isPerson());
    }

    @Test
    void findDigitalAccountPerson() {
        mockServerManager().expectDigitalAccountFindPerson();

        DigitalAccountResource resource = service.findDigitalAccount();

        assertDigitalAccount(resource);
        assertEquals(F, resource.getPersonType());
        assertTrue(resource.isPerson());
        assertFalse(resource.isCompany());
    }

    private void assertDigitalAccount(DigitalAccountResource resource) {
        assertEquals("dac_BF7FCE967887DCF4", resource.getId());
        assertEquals(PAYMENT, resource.getType());
        assertEquals(VERIFIED, resource.getStatus());
        assertEquals("09149649911", resource.getDocument());
        assertEquals(LocalDateTime.parse("2019-03-12T20:31:33.000-03:00", ofPattern(API_ISO_DATE_TIME_PATTERN)), resource.getCreatedOn());
        assertEquals("10000044315", resource.getAccountNumber());
    }

    @Resource
    private JunoDigitalAccountService service;
}
