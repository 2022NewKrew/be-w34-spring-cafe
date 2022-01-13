package com.kakao.cafe.dto.article;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ArticleReqDto {
    private String writer;
    private String title;
    private String contents;
}
