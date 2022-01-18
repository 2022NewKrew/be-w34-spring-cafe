package com.kakao.cafe.domain;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class Article {
    private Long id;
    private final String writer;
    private String title;
    private String content;
    private LocalDateTime creationTime;

    public Article(String writer, String title, String content) {
        this.writer = writer;
        this.title = title;
        this.content = content;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setCreationTime(Timestamp timestamp) {
        creationTime = timestamp.toLocalDateTime();
    }

    public void setCreationTime(LocalDateTime creationTime) {
        this.creationTime = creationTime;
    }

    public Long getId() {
        return id;
    }

    public String getWriter() {
        return writer;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public LocalDateTime getCreationTime() {
        return creationTime;
    }
}
