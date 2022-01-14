package com.kakao.cafe.entity;

import com.kakao.cafe.dto.ArticleRequestDto;

import java.time.LocalDate;

public class Article {
    private int id;
    private String title;
    private String content;
    private LocalDate createdTime;
    private int views;
    private User writer;

    public Article(ArticleRequestDto articleRequestDto, User writer) {
        this.title = articleRequestDto.getTitle();
        this.content = articleRequestDto.getContent();
        this.createdTime = LocalDate.now();
        this.views = 0;
        this.writer = writer;
    }

    public Article(int id, String title, String content, LocalDate createdTime, int views, User writer) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.createdTime = createdTime;
        this.views = views;
        this.writer = writer;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public LocalDate getCreatedTime() {
        return createdTime;
    }

    public int getViews() {
        return views;
    }

    public User getWriter() {
        return writer;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setCreatedTime(LocalDate createdTime) {
        this.createdTime = createdTime;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public void setWriter(User writer) {
        this.writer = writer;
    }
}
