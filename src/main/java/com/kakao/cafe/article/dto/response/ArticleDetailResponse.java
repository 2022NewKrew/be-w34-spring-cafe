package com.kakao.cafe.article.dto.response;

import com.kakao.cafe.article.entity.Article;

import java.time.LocalDateTime;

public class ArticleDetailResponse {

    private final Integer id;
    private final String writer;
    private final String title;
    private final String contents;
    private final LocalDateTime createdAt;

    public ArticleDetailResponse(Article article) {
        this(article.getId(),
             article.getWriter(),
             article.getTitle(),
             article.getContents(),
             article.getCreatedAt());
    }

    public ArticleDetailResponse(Integer id, String writer, String title, String contents, LocalDateTime createdAt) {
        this.id = id;
        this.writer = writer;
        this.title = title;
        this.contents = contents;
        this.createdAt = createdAt;
    }

    public Integer getId() { return this.id; }
    public String getWriter() { return this.writer; }
    public String getTitle() { return this.title; }
    public String getContents() { return this.contents; }
    public LocalDateTime getCreatedAt() { return this.createdAt; }
}
