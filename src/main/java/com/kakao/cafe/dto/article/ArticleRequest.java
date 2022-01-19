package com.kakao.cafe.dto.article;

import com.kakao.cafe.domain.article.Article;

public class ArticleRequest {

    private final String title;
    private final String description;

    public ArticleRequest(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public Article toEntity(Long id) {
        return new Article(id, title, description);
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }
}
