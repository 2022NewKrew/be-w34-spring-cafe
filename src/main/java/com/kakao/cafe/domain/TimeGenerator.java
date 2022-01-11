package com.kakao.cafe.domain;

import java.time.LocalDate;
import java.time.ZoneId;

public class TimeGenerator {
    private TimeGenerator(){

    }

    static String todayDate(){
        LocalDate now = LocalDate.now();
        LocalDate seoulToday = LocalDate.now(ZoneId.of("Asia/Seoul"));
        return seoulToday.toString();
    }
}
