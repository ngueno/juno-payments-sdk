package com.ngueno.juno.sdk.test;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public abstract class AbstractCoreTest extends AbstractTestSuite {

    protected FixtureHelper helper() {
        return helper;
    }

    private FixtureHelper helper = new FixtureHelper();
}
