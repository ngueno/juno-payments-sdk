package com.ngueno.juno.sdk.resources.digitalaccounts;

import static com.ngueno.juno.sdk.config.JunoApiFormats.API_ISO_DATE_TIME_PATTERN;
import static com.ngueno.juno.sdk.resources.base.model.DigitalAccountStatus.VERIFIED;
import static com.ngueno.juno.sdk.resources.base.model.DigitalAccountType.PAYMENT;
import static com.ngueno.juno.sdk.resources.base.model.PersonType.F;
import static java.time.format.DateTimeFormatter.ofPattern;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;

import javax.annotation.Resource;

import com.ngueno.juno.sdk.resources.digitalaccounts.model.DigitalAccountResource;
import com.ngueno.juno.sdk.test.AbstractServiceTest;

import org.junit.jupiter.api.Test;

class JunoDigitalAccountServiceTest extends AbstractServiceTest {

    @Test
    void findDigitalAccount() {

        mockServerManager().expectDigitalAccountFind();

        DigitalAccountResource resource = service.findDigitalAccount();

        assertEquals("dac_BF7FCE967887DCF4", resource.getId());
        assertEquals(PAYMENT, resource.getType());
        assertEquals(VERIFIED, resource.getStatus());
        assertEquals(F, resource.getPersonType());
        assertTrue(resource.isPerson());
        assertFalse(resource.isCompany());
        assertEquals("09149649911", resource.getDocument());
        assertEquals(LocalDateTime.parse("2019-03-12T20:31:33.000-03:00", ofPattern(API_ISO_DATE_TIME_PATTERN)), resource.getCreatedOn());
        assertEquals("10000044315", resource.getAccountNumber());
    }

    @Resource
    private JunoDigitalAccountService service;
}
