package com.kakao.cafe.domain;

import java.time.LocalDateTime;

public class Comment {

    private Integer id;
    private String writer;
    private String contents;
    private Integer qnaId;
    private LocalDateTime createdAt;
    private Boolean deleted = false;

    public Comment(Integer qnaId, String writer, String contents) {
        this.writer = writer;
        this.qnaId = qnaId;
        this.contents = contents;
        this.createdAt = LocalDateTime.now();
    }

    public Comment(Integer id, String writer, String contents, Integer qnaId, LocalDateTime createdAt) {
        this.id = id;
        this.writer = writer;
        this.contents = contents;
        this.qnaId = qnaId;
        this.createdAt = createdAt;
    }

    public Boolean isValidUpdateUser(String userId) {
        return writer.equals(userId);
    }

    public Boolean isValidDeleteUser(String userId) {
        return writer.equals(userId);
    }

    public void delete() {
        this.deleted = true;
    }

    public void updateContents(String contents) {
        this.contents = contents;
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

    public Integer getQnaId() {
        return qnaId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public Boolean getDeleted() {
        return deleted;
    }
}
