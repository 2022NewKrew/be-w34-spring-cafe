package com.kakao.cafe.article.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ArticleResDto {
    private Long id;
    private String title;
    private String writer;
    private String createdAt;
    private int numComments;

    @Builder
    private ArticleResDto(Long id, String title, String writer, String createdAt, int numComments) {
        this.id = id;
        this.title = title;
        this.writer = writer;
        this.createdAt = createdAt;
        this.numComments = numComments;
    }
}
