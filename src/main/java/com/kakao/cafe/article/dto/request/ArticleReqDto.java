package com.kakao.cafe.article.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ArticleReqDto {
    private Long id;
    private String writer;
    private String title;
    private String contents;
}
