package com.kakao.cafe.dto.article;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Builder
@Getter
public class ArticleUpdateDto {
    private Long articleId;
    private String writer;
    private String title;
    private String contents;
}
