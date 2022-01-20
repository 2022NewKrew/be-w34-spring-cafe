package com.kakao.cafe.user.domain;

import java.util.Objects;

public class Password {

    private final String value;

    public Password(String value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other instanceof Password) {
            Password password = (Password) other;
            return value.equals(password.value);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    public String getValue() {
        return value;
    }
}
