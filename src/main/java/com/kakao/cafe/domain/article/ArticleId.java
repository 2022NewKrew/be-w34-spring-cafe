package com.kakao.cafe.domain.article;

import java.util.Objects;

public class ArticleId {

    private final int value;

    public ArticleId(int value) {
        this.value = value;
    }

    public int getValue() {
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
        ArticleId that = (ArticleId) o;
        return value == that.getValue();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getValue());
    }
}
