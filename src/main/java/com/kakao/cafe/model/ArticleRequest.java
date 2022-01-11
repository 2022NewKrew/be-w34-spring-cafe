package com.kakao.cafe.model;

public class ArticleRequest {
    private final String title;
    private final String content;

    public ArticleRequest(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }
}
