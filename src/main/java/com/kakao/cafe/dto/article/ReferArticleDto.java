package com.kakao.cafe.dto.article;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ReferArticleDto {
    private Integer articleId;
    private String writer;
    private String title;
    private String contents;
}
