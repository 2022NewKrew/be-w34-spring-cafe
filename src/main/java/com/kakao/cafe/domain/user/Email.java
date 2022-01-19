package com.kakao.cafe.domain.user;

import java.util.Objects;

public class Email {

    private final String value;

    public Email(String value) {
        if (value == null || value.isBlank() || !value.matches("^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$")) {
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
        Email that = (Email) o;
        return value.equals(that.getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
