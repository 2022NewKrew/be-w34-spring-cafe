package com.kakao.cafe.article.domain;

public class ArticleId {

    private final long value;

    public ArticleId(long value) {
        this.value = value;
    }

    public long getValue() {
        return value;
    }
}
