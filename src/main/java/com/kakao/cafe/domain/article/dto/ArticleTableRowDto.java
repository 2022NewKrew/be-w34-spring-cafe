package com.kakao.cafe.domain.article.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor @Builder
@Getter
public class ArticleTableRowDto {
    private Long id;
    private Long userId;
    private String author;
    private String title;
    private String content;
    private String createdAt;
    private Long viewCount;
}
