package com.kakao.cafe.util;

public final class Checker {

    private Checker() {}

    public static void checkIntMinMax (
            final int min,
            final int max
    ) throws IllegalStateException
    {
        if (min < 0 || max < 0) {
            throw new IllegalStateException("min or max is not 0 or positive integer! - [" + min + ", " + max + "]");
        }
        if (min > max) {
            throw new IllegalStateException("min value(" + min + ") is greater then max value(" + max + ")");
        }
    }
}
