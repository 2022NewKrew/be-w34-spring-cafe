package com.kakao.cafe.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public final class DateTimeFormatUtil {

    public static String format(LocalDateTime dateTime) {
        return dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd  HH:mm:ss"));
    }
}
