package com.kakao.cafe.article.entity;

import com.kakao.cafe.article.dto.request.ArticleCreateRequest;

import java.time.LocalDateTime;

public class Article {

    private Integer id;
    private String writer;
    private String title;
    private String contents;
    private LocalDateTime createdAt;

    public Article(ArticleCreateRequest req) {
        this(req.getWriter(),
             req.getTitle(),
             req.getContents());
    }

    public Article(String writer, String title, String contents) {
        this.writer = writer;
        this.title = title;
        this.contents = contents;
    }

    public Integer getId() { return this.id; }
    public String getWriter() { return this.writer; }
    public String getTitle() { return this.title; }
    public String getContents() { return this.contents; }
    public LocalDateTime getCreatedAt() { return this.createdAt; }

    public void setId(Integer id) { this.id = id; }
    public void setWriter(String writer) { this.writer = writer; }
    public void setTitle(String title) { this.title = title; }
    public void setContents(String contents) { this.contents = contents; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
