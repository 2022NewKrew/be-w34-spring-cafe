package com.kakao.cafe.domain.article;

public class Content {

    private final String value;

    public Content(String value) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("잘못된 입력입니다.");
        }
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
