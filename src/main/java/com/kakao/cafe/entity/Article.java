package com.kakao.cafe.entity;

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
