package com.ngueno.juno.sdk.resources.balance;

import static org.junit.jupiter.api.Assertions.assertEquals;

import javax.annotation.Resource;

import org.junit.jupiter.api.Test;

import com.ngueno.juno.sdk.resources.balance.model.BalanceResource;
import com.ngueno.juno.sdk.test.AbstractSpringBootTest;

class JunoBalanceServiceTest extends AbstractSpringBootTest {

    @Test
    void getBalance() {
        mockServerManager().expectBalance();

        BalanceResource resource = service.getBalance();

        assertBigDecimal("12250.29", resource.getBalance());
        assertBigDecimal("1545.62", resource.getWithheldBalance());
        assertBigDecimal("10704.67", resource.getTransferableBalance());
        assertEquals("https://dummy/api-integration/balance", resource.getLinks("self").getHref());
    }

    @Resource
    private JunoBalanceService service;
}
