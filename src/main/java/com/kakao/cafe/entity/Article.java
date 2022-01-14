package com.kakao.cafe.entity;

import javax.validation.constraints.NotBlank;

public class Article {
    private Integer articleId;
    private String title;
    private String content;

    public Article(Integer articleId, String title, String content) {
        this.articleId = articleId;
        this.title = title;
        this.content = content;
    }

    public Integer getArticleId() {
        return articleId;
    }
}
