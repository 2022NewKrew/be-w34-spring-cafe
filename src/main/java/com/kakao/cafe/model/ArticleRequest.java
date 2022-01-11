package com.kakao.cafe.model;

public class ArticleRequest {
    private String writer;
    private String title;
    private String contents;

    public ArticleRequest(String writer, String title, String contents) {
        this.writer = writer;
        this.title = title;
        this.contents = contents;
    }

    public String getWriter() {
        return this.writer;
    }

    public String getTitle() {
        return title;
    }

    public String getContents() {
        return contents;
    }
}
