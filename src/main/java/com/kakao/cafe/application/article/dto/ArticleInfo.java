package com.kakao.cafe.application.article.dto;

import com.kakao.cafe.domain.article.Article;

public class ArticleInfo {

    private final int id;
    private final String writer;
    private final String title;
    private final String createdAt;

    public ArticleInfo(int id, String writer, String title, String createdAt) {
        this.id = id;
        this.writer = writer;
        this.title = title;
        this.createdAt = createdAt;
    }

    public int getId() {
        return id;
    }

    public String getWriter() {
        return writer;
    }

    public String getTitle() {
        return title;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public static ArticleInfo from(Article article) {
        return new ArticleInfo(article.getId(), article.getWriter(), article.getTitle(), article.getCreatedAt());
    }
}
