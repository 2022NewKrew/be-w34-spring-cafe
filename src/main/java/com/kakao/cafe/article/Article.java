package com.kakao.cafe.article;

public class Article {

    private String writer;

    private String title;

    private String contents;

    public Article(ArticleRequest articleRequest) {
        this.writer = articleRequest.getWriter();
        this.title = articleRequest.getTitle();
        this.contents = articleRequest.getContents();
    }

    public String getWriter() {
        return writer;
    }

    public String getTitle() {
        return title;
    }

    public String getContents() {
        return contents;
    }

}
