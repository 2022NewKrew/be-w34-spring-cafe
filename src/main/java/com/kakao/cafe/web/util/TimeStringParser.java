package com.kakao.cafe.web.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TimeStringParser {
    private static final DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public static String parseTimeToString(LocalDateTime localDateTime) {
        return localDateTime.format(format);
    }

    public static LocalDateTime parseStringToTime(String string) {
        return LocalDateTime.parse(string, format);
    }
}
