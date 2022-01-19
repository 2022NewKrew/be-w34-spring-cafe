package com.kakao.cafe.dto.article;

import lombok.Getter;

@Getter
public class ArticleContentDto {

    private final String title;
    private final String content;
    private final String writer;
    private final String createdAt;
    private final int viewCount;

    public ArticleContentDto(String title, String content, String writer, String createdAt, int viewCount) {
        this.title = title;
        this.content = content.replace("\n", "<br>");
        this.writer = writer;
        this.createdAt = createdAt;
        this.viewCount = viewCount;
    }
}
