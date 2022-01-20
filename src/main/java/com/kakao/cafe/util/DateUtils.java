package com.kakao.cafe.util;

import java.time.LocalDateTime;

public class DateUtils {
    private DateUtils() {

    }

    public static String getLocalDateTimeNow() {
        return LocalDateTime.now().toString();
    }

}
