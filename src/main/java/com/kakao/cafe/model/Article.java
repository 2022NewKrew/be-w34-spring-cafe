package com.kakao.cafe.model;

public class Article {
    private final int id;
    private final String title;
    private final String content;

    public Article(ArticleRequest articleRequest, int id) {
        this.id = id;
        this.title = articleRequest.getTitle();
        this.content = articleRequest.getContent();
    }
}
