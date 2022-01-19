package com.kakao.cafe.user.domain;

import java.util.Objects;

public class Password {

    private final String value;

    public Password(String value) {
        this.value = value;
    }


    @Override
    public boolean equals(Object target) {
        if (this == target) {
            return true;
        }
        if (target == null || getClass() != target.getClass()) {
            return false;
        }
        Password password = (Password) target;
        return Objects.equals(value, password.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    public String getValue() {
        return value;
    }
}
