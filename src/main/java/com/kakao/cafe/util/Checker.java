package com.kakao.cafe.util;

import java.util.Objects;

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

    public static void checkString(
            final String name,
            final String str,
            final String regex,
            final int min,
            final int max
    ) throws IllegalStateException, IllegalArgumentException
    {
        checkString(name, str, min, max);
        final String trimmed = Objects.requireNonNull(str).trim();
        if (!trimmed.matches(regex)) {
            throw new IllegalArgumentException(name + " is invalid!");
        }
    }

    public static void checkString(
            final String name,
            final String str,
            final int min,
            final int max
    ) throws IllegalStateException, IllegalArgumentException
    {
        Objects.requireNonNull(name);
        checkIntMinMax(min, max);
        final int len = Objects.requireNonNull(str).trim().length();
        if (len < min || len > max) {
            throw new IllegalArgumentException(name + " length is out of bound [" + min + ", " + max + "]");
        }
    }
}
