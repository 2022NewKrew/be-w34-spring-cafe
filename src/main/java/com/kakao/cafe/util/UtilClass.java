package com.kakao.cafe.util;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class UtilClass {
    private UtilClass() { }

    public static String getLocalDateTimeNow(){
        return LocalDateTime.now().toString();
    }

    public static List<Integer> makePageList(int size){
        List<Integer> pageList = new ArrayList<>();
        for(int i = 1; i <= ((size-1) / 10) + 1; i++){
            pageList.add(i);
        }
        return pageList;
    }
}
