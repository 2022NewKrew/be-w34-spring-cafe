package com.kakao.cafe.domain;

import com.kakao.cafe.exception.AlreadyDeletedQnaException;

import java.time.LocalDateTime;

public class Qna {

    private Integer id;
    private String writer;
    private String title;
    private String contents;
    private Boolean deleted;
    private LocalDateTime createdAt;

    public Qna(String writer, String title, String contents) {
        this.writer = writer;
        this.title = title;
        this.contents = contents;
        this.deleted = false;
        this.createdAt = LocalDateTime.now();
    }

    public Qna(Integer id, String writer, String title, String contents, LocalDateTime createdAt) {
        this.id = id;
        this.writer = writer;
        this.title = title;
        this.contents = contents;
        this.deleted = false;
        this.createdAt = createdAt;
    }

    public void updateQna(String title, String contents) {
        this.title = title;
        this.contents = contents;
    }

    public void delete() {
        if (deleted) {
            throw new AlreadyDeletedQnaException(id);
        }

        deleted = true;
    }

    public Boolean isValidUpdateUser(String userId) {
        return writer.equals(userId);
    }

    public Boolean isValidDeleteUser(String userId) {
        return writer.equals(userId);
    }

    public Integer getId() {
        return id;
    }

    public String getWriter() {
        return writer;
    }

    public String getTitle() {
        return title;
    }

    public String getContents() {
        return contents;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
