package com.kakao.cafe.domain.common;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Deleted {

    private static final Map<Boolean, Deleted> ARTICLE_DELETED_CACHE = new HashMap<>();

    static {
        ARTICLE_DELETED_CACHE.put(true, new Deleted(true));
        ARTICLE_DELETED_CACHE.put(false, new Deleted(false));
    }

    private final boolean value;

    private Deleted(boolean value) {
        this.value = value;
    }

    public static Deleted from(boolean value) {
        return ARTICLE_DELETED_CACHE.get(value);
    }

    public boolean getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Deleted that = (Deleted) o;
        return value == that.getValue();
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
