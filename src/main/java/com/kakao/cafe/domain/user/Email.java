package com.kakao.cafe.domain.user;

public class Email {

    private final String value;

    public Email(String value) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("잘못된 입력입니다.");
        }
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
