package com.kakao.cafe.model.user;

import com.kakao.cafe.utility.NullChecker;

import java.util.Objects;

public class Name {
    private static final int ALLOWED_LENGTH_NAME = 8;

    private final String value;

    public Name(String name) {
        checkName(name);

        this.value = name;
    }

    private void checkName(String name) {
        NullChecker.checkNotNull(name);

        if (name.length() > ALLOWED_LENGTH_NAME) {
            throw new IllegalArgumentException(String.format("이름의 길이는 %s 이하여야 합니다.", ALLOWED_LENGTH_NAME));
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Name name = (Name) o;
        return Objects.equals(value, name.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    public String getValue() {
        return value;
    }
}
