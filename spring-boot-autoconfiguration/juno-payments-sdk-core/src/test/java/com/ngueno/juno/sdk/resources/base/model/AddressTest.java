package com.ngueno.juno.sdk.resources.base.model;

import static com.ngueno.juno.sdk.test.FixtureHelper.ADDRESS_NUMBER;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.ngueno.juno.sdk.test.AbstractCoreTest;

import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

class AddressTest extends AbstractCoreTest {

    @Test
    void getNumber() {
        Address address = helper().createAddress();
        assertEquals(ADDRESS_NUMBER, address.getNumber());
        ReflectionTestUtils.setField(address, "number", null);
        assertEquals("N/A", address.getNumber());
    }

}
