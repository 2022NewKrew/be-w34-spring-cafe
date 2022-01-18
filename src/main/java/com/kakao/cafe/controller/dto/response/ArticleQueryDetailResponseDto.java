package com.kakao.cafe.controller.dto.response;

import com.kakao.cafe.domain.Article;

public class ArticleQueryDetailResponseDto {

    private final Long id;
    private final String title;
    private final String contents;
    private final String writerId;

    public ArticleQueryDetailResponseDto(Article article) {
        this.id = article.getId();
        this.title = article.getTitle();
        this.contents = article.getContents();
        this.writerId = article.getWriter().getUserId();
    }
}
