package com.kakao.cafe.article.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ArticleResDto {
    private String title;
    private String writer;
    private String createdAt;
    private int numComments;

    @Builder
    private ArticleResDto(String title, String writer, String createdAt, int numComments) {
        this.title = title;
        this.writer = writer;
        this.createdAt = createdAt;
        this.numComments = numComments;
    }
}
