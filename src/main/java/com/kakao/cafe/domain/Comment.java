package com.kakao.cafe.domain;

import java.time.LocalDateTime;

public class Comment {

    private Integer id;
    private String writer;
    private String contents;
    private Integer qnaIndex;
    private LocalDateTime createdAt;

    public Comment(Integer qnaIndex, String writer, String contents) {
        this.writer = writer;
        this.qnaIndex = qnaIndex;
        this.contents = contents;
        this.createdAt = LocalDateTime.now();
    }

    public Comment(Integer id, String writer, String contents, Integer qnaIndex, LocalDateTime createdAt) {
        this.id = id;
        this.writer = writer;
        this.contents = contents;
        this.qnaIndex = qnaIndex;
        this.createdAt = createdAt;
    }

    public Integer getId() {
        return id;
    }

    public String getWriter() {
        return writer;
    }

    public String getContents() {
        return contents;
    }

    public Integer getQnaIndex() {
        return qnaIndex;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
