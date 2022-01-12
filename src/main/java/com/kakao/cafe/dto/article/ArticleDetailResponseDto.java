package com.kakao.cafe.dto.article;

public class ArticleDetailResponseDto {

    private final String title;
    private final String content;
    private final String writer;
    private final String createdAt;
    private final int viewCount;

    public ArticleDetailResponseDto(String title, String content, String writer, String createdAt, int viewCount) {
        this.title = title;
        this.content = content;
        this.writer = writer;
        this.createdAt = createdAt;
        this.viewCount = viewCount;
    }
}
