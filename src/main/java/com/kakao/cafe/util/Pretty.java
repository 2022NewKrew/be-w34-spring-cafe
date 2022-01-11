package com.kakao.cafe.util;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Objects;

public final class Pretty {

    private Pretty() {}

    public static String epochSecond(final long epoch, final Locale locale, final ZoneId zoneId) {
        Objects.requireNonNull(locale);
        Objects.requireNonNull(zoneId);
        return DateTimeFormatter.ofPattern("uuuu-MM-dd HH:mm")
                .withLocale(locale)
                .withZone(zoneId)
                .format(Instant.ofEpochSecond(epoch));
    }

    public static String[] splitByNewLine(final String text) {
        Objects.requireNonNull(text);
        return text.split("\r?\n");
    }
}
