package com.kakao.cafe.article.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ArticleDetailResDto {
    private Long id;
    private String title;
    private String writer;
    private String contents;
    private String createdAt;

    @Builder
    private ArticleDetailResDto(Long id, String title, String writer, String contents, String createdAt) {
        this.id = id;
        this.title = title;
        this.writer = writer;
        this.contents = contents;
        this.createdAt = createdAt;
    }
}
