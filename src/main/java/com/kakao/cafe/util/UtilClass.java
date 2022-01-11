package com.kakao.cafe.util;

import com.kakao.cafe.domain.article.Page;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class UtilClass {
    private UtilClass() { }

    public static String getLocalDateTimeNow(){
        return LocalDateTime.now().toString();
    }

    public static List<Page> makePageList(int size){
        List<Page> pageList = new ArrayList<>();
        for(int i = 1; i <= ((size-1) / 10) + 1; i++){
            pageList.add(new Page(i));
        }
        return pageList;
    }
}
