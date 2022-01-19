package com.kakao.cafe.dto.article;

import com.kakao.cafe.domain.article.Article;

public class ArticleDto {

    private final Long articleId;
    private final Long authorId;
    private final String author;
    private final String title;

    public ArticleDto(Article article) {
        this.articleId = article.getId();
        this.authorId = article.getAuthorId();
        this.author = article.getAuthor();
        this.title = article.getTitle();
    }

    public Long getAuthorId() {
        return authorId;
    }
}
