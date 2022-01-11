package com.kakao.cafe.domain.article;

public class Title {

    private final String value;

    public Title(String value) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("잘못된 입력입니다.");
        }
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
