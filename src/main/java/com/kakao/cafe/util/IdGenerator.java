package com.kakao.cafe.util;

import java.util.Random;

public class IdGenerator {
    private static Long id = (long) Math.abs(new Random().nextInt());

    public static Long createId(){
        synchronized (id){
            return id++;
        }
    }
}
