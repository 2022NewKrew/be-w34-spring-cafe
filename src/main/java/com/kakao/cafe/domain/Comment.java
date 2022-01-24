package com.kakao.cafe.domain;

import com.kakao.cafe.exception.AlreadyDeletedCommentException;

import java.time.LocalDateTime;

public class Comment {

    private Integer id;
    private String writer;
    private String contents;
    private Integer qnaId;
    private LocalDateTime createdAt;
    private Boolean deleted;

    public Comment(Integer qnaId, String writer, String contents) {
        this.writer = writer;
        this.qnaId = qnaId;
        this.contents = contents;
        this.createdAt = LocalDateTime.now();
        this.deleted = false;
    }

    public Comment(Integer id, String writer, String contents, Integer qnaId, LocalDateTime createdAt) {
        this.id = id;
        this.writer = writer;
        this.contents = contents;
        this.qnaId = qnaId;
        this.createdAt = createdAt;
        this.deleted = false;
    }

    public Boolean isValidUpdateUser(String userId) {
        return writer.equals(userId);
    }

    public Boolean isValidDeleteUser(String userId) {
        return writer.equals(userId);
    }

    public void delete() {
        if (deleted) {
            throw new AlreadyDeletedCommentException(id);
        }

        deleted = true;
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
