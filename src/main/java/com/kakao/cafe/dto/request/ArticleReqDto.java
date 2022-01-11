package com.kakao.cafe.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ArticleReqDto {
    private String writer;
    private String title;
    private String contents;
}
