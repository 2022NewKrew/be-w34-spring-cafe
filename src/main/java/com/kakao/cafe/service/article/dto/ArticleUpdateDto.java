package com.kakao.cafe.service.article.dto;

public class ArticleUpdateDto {

    private final String userId;
    private final String title;
    private final String contents;

    public ArticleUpdateDto(String userId, String title, String contents) {
        this.userId = userId;
        this.title = title;
        this.contents = contents;
    }

    public String getUserId() {
        return userId;
    }

    public String getTitle() {
        return title;
    }

    public String getContents() {
        return contents;
    }
}
