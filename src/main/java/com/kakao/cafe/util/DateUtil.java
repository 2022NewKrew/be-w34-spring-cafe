package com.kakao.cafe.util;

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class DateUtil {
    private DateUtil() {}

    public static Calendar getCurrentCalendar() {
        return new GregorianCalendar();
    }
}
