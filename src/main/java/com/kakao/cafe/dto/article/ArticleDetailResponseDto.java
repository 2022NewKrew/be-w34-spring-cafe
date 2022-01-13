package com.kakao.cafe.dto.article;

public class ArticleDetailResponseDto {

    private final String title;
    private final String content;
    private final String writer;
    private final String createdAt;
    private final int viewCount;

    public ArticleDetailResponseDto(String title, String content, String writer, String createdAt, int viewCount) {
        this.title = title;
        this.content = content.replace("\n", "<br>");
        this.writer = writer;
        this.createdAt = createdAt;
        this.viewCount = viewCount;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getWriter() {
        return writer;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public int getViewCount() {
        return viewCount;
    }
}
