package com.kakao.cafe.utility;

public class NullChecker {

    public static void checkNotNull(Object target) {
        if (target == null) {
            throw new IllegalArgumentException("널 값을 가질 수 없습니다!");
        }
    }
}
