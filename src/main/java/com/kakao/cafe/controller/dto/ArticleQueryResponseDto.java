package com.kakao.cafe.controller.dto;

import com.kakao.cafe.domain.Article;

public class ArticleQueryResponseDto {

    private final Long id;
    private final String title;
    private final String writerId;

    public ArticleQueryResponseDto(Article article) {
        this.id = article.getId();
        this.title = article.getTitle();
        this.writerId = article.getWriter().getUserId();
    }
}
