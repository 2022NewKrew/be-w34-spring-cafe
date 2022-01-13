package com.kakao.cafe.web.article.dto;

import com.kakao.cafe.domain.article.Article;

public class ArticleCreateRequest {
    private final String title;
    private final String content;

    public ArticleCreateRequest(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public Article toEntity() {
        Article article = new Article();
        article.setTitle(title);
        article.setContent(content);

        return article;
    }
}
