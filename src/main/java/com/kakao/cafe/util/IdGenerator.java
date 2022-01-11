package com.kakao.cafe.util;

public class IdGenerator {
    private static Long id = 1L;

    public static Long createId(){
        synchronized (id){
            return id++;
        }
    }
}
