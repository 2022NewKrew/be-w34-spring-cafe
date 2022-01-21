package com.kakao.cafe.dto.article;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Builder
@Setter
public class ArticleRequest {
    private String writer;
    private String title;
    private String contents;
}
