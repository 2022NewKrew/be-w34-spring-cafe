package com.kakao.cafe.domain;

import com.kakao.cafe.dto.QuestionCreateRequest;

import java.time.LocalDateTime;

public class Question {
    private Long id;
    private User writer;
    private String title;
    private String contents;
    private LocalDateTime createdDateTime;

    public Question(){
        createdDateTime = LocalDateTime.now();
    }

    public Question(QuestionCreateRequest questionCreateRequest, User writer) {
        createdDateTime = LocalDateTime.now();
        this.writer = writer;
        this.title = questionCreateRequest.getTitle();
        this.contents = questionCreateRequest.getContents();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getWriter() {
        return writer;
    }

    public void setWriter(User writer) {
        this.writer = writer;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }


    public LocalDateTime getCreatedDateTime() {
        return createdDateTime;
    }

    public void setCreatedDateTime(LocalDateTime createdDateTime) {
        this.createdDateTime = createdDateTime;
    }
}
