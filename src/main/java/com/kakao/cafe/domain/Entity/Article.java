package com.kakao.cafe.domain.Entity;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Article {
    private int articleId;
    private String writer;
    private String title;
    private String contents;
}
