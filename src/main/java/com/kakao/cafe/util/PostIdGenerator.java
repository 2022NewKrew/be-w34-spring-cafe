package com.kakao.cafe.util;

public class PostIdGenerator {
    private static Long id = 0L;

    public static Long nextId() {
        return ++id;
    }
}
