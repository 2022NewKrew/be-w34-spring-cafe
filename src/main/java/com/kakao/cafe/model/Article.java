package com.kakao.cafe.model;

public class Article {
    private final int id;
    private final String writer;
    private final String title;
    private final String contents;

    public Article(ArticleRequest articleRequest, int id) {
        this.id = id;
        this.writer = articleRequest.getWriter();
        this.title = articleRequest.getTitle();
        this.contents = articleRequest.getContents();
    }
}
