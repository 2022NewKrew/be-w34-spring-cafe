package com.kakao.cafe.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TimeFormatter {
    private final static DateTimeFormatter standardDateTime = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static String formatStandardDateTime(LocalDateTime localDateTime) {
        return localDateTime.format(standardDateTime);
    }
}
