package com.kakao.cafe.dto.article;

import com.kakao.cafe.domain.article.Article;

public class ArticleDto {

    private final Long id;
    private final String title;
    private final String description;

    public ArticleDto(Article article) {
        this.id = article.getId();
        this.title = article.getTitle();
        this.description = article.getDescription();
    }
}
