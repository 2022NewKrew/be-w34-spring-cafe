package com.kakao.cafe.dto.article;

import com.kakao.cafe.domain.article.Article;

public class ArticleRequest {

    private final Long authorId;
    private final String title;
    private final String description;

    public ArticleRequest(Long authorId, String title, String description) {
        this.authorId = authorId;
        this.title = title;
        this.description = description;
    }

    public Article toEntity() {
        return new Article(authorId, title, description);
    }
}
