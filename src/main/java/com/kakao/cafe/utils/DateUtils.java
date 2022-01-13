package com.kakao.cafe.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public final class DateUtils {
    public static String getCurrentTime() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return simpleDateFormat.format(new Date());
    }
}
