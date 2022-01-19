package com.kakao.cafe.domain.article;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Content that = (Content) o;
        return value.equals(that.getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
