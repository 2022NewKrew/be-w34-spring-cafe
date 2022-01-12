package com.kakao.cafe.dto.article;

public class ArticleListResponseDto {

    private final String articleId;
    private final String title;
    private final String writer;
    private final String createdAt;
    private final int viewCount;

    public ArticleListResponseDto(String articleId, String title, String writer, String createdAt, int viewCount) {
        this.articleId = articleId;
        this.title = title;
        this.writer = writer;
        this.createdAt = createdAt;
        this.viewCount = viewCount;
    }
}
