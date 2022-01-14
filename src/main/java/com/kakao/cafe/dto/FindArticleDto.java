package com.kakao.cafe.dto;

public class FindArticleDto {

    private final Integer articleId;

    public FindArticleDto(Integer articleId) {
        this.articleId = articleId;
    }

    public Integer getArticleId() {
        return articleId;
    }
}
