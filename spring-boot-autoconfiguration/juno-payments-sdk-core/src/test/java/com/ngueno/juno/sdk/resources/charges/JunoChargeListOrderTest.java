package com.ngueno.juno.sdk.resources.charges;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import com.ngueno.juno.sdk.resources.charges.model.JunoChargeListOrder;
import com.ngueno.juno.sdk.test.AbstractCoreTest;

import org.junit.jupiter.api.Test;

class JunoChargeListOrderTest extends AbstractCoreTest {

    @Test
    void values() {
        assertThat(List.of(JunoChargeListOrder.values()),
                hasItems(JunoChargeListOrder.ID, JunoChargeListOrder.AMOUNT, JunoChargeListOrder.DUE_DATE, JunoChargeListOrder.PAYMENT_DATE));
    }

    @Test
    void getters() {
        assertEquals("id", JunoChargeListOrder.ID.getOrder());
        assertEquals("amount", JunoChargeListOrder.AMOUNT.getOrder());
        assertEquals("dueDate", JunoChargeListOrder.DUE_DATE.getOrder());
        assertEquals("paymentDate", JunoChargeListOrder.PAYMENT_DATE.getOrder());
    }

    @Test
    void valueOf() {
        assertEquals(JunoChargeListOrder.AMOUNT, JunoChargeListOrder.valueOf("AMOUNT"));
    }
}