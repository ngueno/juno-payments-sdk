package com.ngueno.juno.sdk.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.ngueno.juno.sdk.config.JunoApiFormats;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class CalendarUtils {

    public static final DateTimeFormatter API_DATE_FORMATTER = DateTimeFormatter.ofPattern(JunoApiFormats.API_DATE_PATTERN);
    public static final DateTimeFormatter API_DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(JunoApiFormats.API_DATE_TIME_PATTERN);
    public static final DateTimeFormatter API_DATE_TIME_ISO_FORMATTER = DateTimeFormatter.ofPattern(JunoApiFormats.API_ISO_DATE_TIME_PATTERN);

    public static LocalDate parseDate(String date) {
        return date != null ? LocalDate.parse(date, API_DATE_FORMATTER) : null;
    }

    public static LocalDateTime parseDateTime(String date) {
        return date != null ? LocalDateTime.parse(date, API_DATE_TIME_FORMATTER) : null;
    }

    public static LocalDateTime parseDateTimeIsoFormat(String date) {
        return date != null ? LocalDateTime.parse(date, API_DATE_TIME_ISO_FORMATTER) : null;
    }

    public static String formatDate(LocalDate localDate) {
        return localDate != null ? localDate.format(API_DATE_FORMATTER) : null;
    }

    public static String formatDateTime(LocalDateTime localDateTime) {
        return localDateTime != null ? localDateTime.format(API_DATE_TIME_FORMATTER) : null;
    }
}
