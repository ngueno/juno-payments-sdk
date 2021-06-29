package com.ngueno.juno.sdk.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import com.ngueno.juno.sdk.test.AbstractTest;

import org.junit.jupiter.api.Test;

class CalendarUtilsTest extends AbstractTest {

    @Test
    void parseDate() {
        assertEquals(LocalDate.of(2021, 6, 27), CalendarUtils.parseDate("2021-06-27"));
        assertEquals(LocalDate.of(1999, 1, 20), CalendarUtils.parseDate("1999-01-20"));

        assertThrows(DateTimeParseException.class, () -> CalendarUtils.parseDate("2021-06-27 18:04:00"));
        assertThrows(DateTimeParseException.class, () -> CalendarUtils.parseDate("27/06/2021 18:04:00"));
        assertThrows(DateTimeParseException.class, () -> CalendarUtils.parseDate("27/06/2021"));
        assertThrows(DateTimeParseException.class, () -> CalendarUtils.parseDate("27/2021"));
        assertThrows(DateTimeParseException.class, () -> CalendarUtils.parseDate("2021"));
    }

    @Test
    void parseDateNull() {
        assertNull(CalendarUtils.parseDate(null));
    }

    @Test
    void parseDateTime() {
        assertEquals(LocalDateTime.of(2021, 6, 27, 18, 06, 24), CalendarUtils.parseDateTime("2021-06-27 18:06:24"));
        assertEquals(LocalDateTime.of(1999, 1, 20, 5, 6, 59), CalendarUtils.parseDateTime("1999-01-20 05:06:59"));

        assertThrows(DateTimeParseException.class, () -> CalendarUtils.parseDateTime("1999-01-20 05:06:60"));
        assertThrows(DateTimeParseException.class, () -> CalendarUtils.parseDateTime("27/06/2021 18:04:00"));
        assertThrows(DateTimeParseException.class, () -> CalendarUtils.parseDateTime("27/2021"));
        assertThrows(DateTimeParseException.class, () -> CalendarUtils.parseDateTime("2021"));
    }

    @Test
    void parseDateTimeNull() {
        assertNull(CalendarUtils.parseDateTime(null));
    }

    @Test
    void formatDate() {
        assertEquals("2021-06-27", CalendarUtils.formatDate(LocalDate.of(2021, 6, 27)));
        assertEquals("1999-01-20", CalendarUtils.formatDate(LocalDate.of(1999, 1, 20)));
    }

    @Test
    void formatDateNull() {
        assertNull(CalendarUtils.formatDate(null));
    }

    @Test
    void formatDateTime() {
        assertEquals("2021-06-27 18:06:24", CalendarUtils.formatDateTime(LocalDateTime.of(2021, 6, 27, 18, 06, 24)));
        assertEquals("1999-01-20 05:06:59", CalendarUtils.formatDateTime(LocalDateTime.of(1999, 1, 20, 5, 6, 59)));
    }

    @Test
    void formatDateTimeNull() {
        assertNull(CalendarUtils.formatDateTime(null));
    }

}
