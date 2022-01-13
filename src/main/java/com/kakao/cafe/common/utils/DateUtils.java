package com.kakao.cafe.common.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    public static String getCurrentDate(){
        return dateFormat.format(new Date());
    }
}
