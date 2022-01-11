package com.kakao.cafe.article.dto.request;

public class ArticleCreateRequest {

    private String writer;
    private String title;
    private String contents;

    public ArticleCreateRequest() {}

    public ArticleCreateRequest(String writer, String title, String contents) {
        this.writer = writer;
        this.title = title;
        this.contents = contents;
    }

    public String getWriter() { return this.writer; }
    public String getTitle() { return this.title; }
    public String getContents() { return this.contents; }
}
