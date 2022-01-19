package com.kakao.cafe.article.dto;

import com.kakao.cafe.article.model.Article;

import java.time.format.DateTimeFormatter;

public class ArticleDto {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private final Long id;
    private final String title;
    private final String content;
    private final String createdAt;

    public ArticleDto(Article article) {
        this(article.getId(), article.getTitle(), article.getTitle(), FORMATTER.format(article.getCreatedAt()));
    }

    public ArticleDto(Long id, String title, String content, String createdAt) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getCreatedAt() {
        return createdAt;
    }
}
