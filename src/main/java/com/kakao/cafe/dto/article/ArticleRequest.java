package com.kakao.cafe.dto.article;

public class ArticleRequest {

    private final String title;
    private final String description;

    public ArticleRequest(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }
}
