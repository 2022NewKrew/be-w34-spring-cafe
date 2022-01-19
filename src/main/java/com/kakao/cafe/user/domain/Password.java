package com.kakao.cafe.user.domain;

import java.util.Objects;

public class Password {

    private final String value;

    public Password(String value) {
        this.value = value;
    }

    public boolean isSame(Password targetPassword) {
        return Objects.equals(value, targetPassword.value);
    }

    public String getValue() {
        return value;
    }
}
