package com.kakao.cafe.controller.dto;

import com.kakao.cafe.domain.Article;

public class ArticleQueryDetailResponseDto {

    private final String title;
    private final String contents;
    private final String writerName;

    public ArticleQueryDetailResponseDto(Article article) {
        this.title = article.getTitle();
        this.contents = article.getContents();
        this.writerName = article.getWriter().getName();
    }
}
