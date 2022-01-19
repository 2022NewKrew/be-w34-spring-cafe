package com.kakao.cafe.domain.user;

import java.util.Objects;

public class Password {

    private final String value;

    public Password(String value) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("잘못된 입력입니다.");
        }
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Password that = (Password) o;
        return value.equals(that.getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
