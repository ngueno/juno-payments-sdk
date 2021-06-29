package com.ngueno.juno.sdk.test;

import static org.hamcrest.MatcherAssert.assertThat;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.ngueno.juno.sdk.utils.CalendarUtils;

@ExtendWith(MockitoExtension.class)
public abstract class AbstractTest {

    protected void assertBigDecimal(String expected, BigDecimal actual) {
        assertThat(actual, Matchers.comparesEqualTo(bd(expected)));
    }

    protected BigDecimal bd(String value) {
        BigDecimal bd = new BigDecimal(value);
        bd.setScale(2);
        return bd;
    }

    protected LocalDate parseDate(String date) {
        return CalendarUtils.parseDate(date);
    }

    protected LocalDateTime parseDateTime(String date) {
        return CalendarUtils.parseDateTime(date);
    }

    protected String formatDate(LocalDate localDate) {
        return CalendarUtils.formatDate(localDate);
    }

    protected String formatDateTime(LocalDateTime localDateTime) {
        return CalendarUtils.formatDateTime(localDateTime);
    }

    protected FixtureHelper helper() {
        return helper;
    }

    private FixtureHelper helper = new FixtureHelper();
}
