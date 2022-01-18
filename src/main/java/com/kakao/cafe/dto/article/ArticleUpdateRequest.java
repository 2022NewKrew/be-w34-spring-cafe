package com.kakao.cafe.dto.article;

public class ArticleUpdateRequest {

    private final String title;
    private final String description;

    public ArticleUpdateRequest(String title, String description) {
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
