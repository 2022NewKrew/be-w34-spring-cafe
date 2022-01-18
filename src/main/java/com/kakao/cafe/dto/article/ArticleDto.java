package com.kakao.cafe.dto.article;

import com.kakao.cafe.domain.article.Article;

public class ArticleDto {

    private final Long articleId;
    private final Long authorId;
    private final String author;
    private final String title;
    private final String description;

    public ArticleDto(Article article) {
        this.articleId = article.getId();
        this.authorId = article.getAuthorId();
        this.author = article.getAuthor();
        this.title = article.getTitle();
        this.description = article.getDescription();
    }

    public Long getAuthorId() {
        return authorId;
    }
}
