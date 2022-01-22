package com.kakao.cafe.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DateUtils {
    private DateUtils() {

    }

    public static String getLocalDateTimeNow() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(Constant.DATE_FORMAT);
        return simpleDateFormat.format(Calendar.getInstance().getTime());
    }

}
