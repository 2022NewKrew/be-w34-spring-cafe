package com.kakao.cafe.domain.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TypeConverter {

    public static String convertLocalDateTimeToString(LocalDateTime localDateTime) {
        return DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(localDateTime);
    }
}
