package com.kakao.cafe.domain;

import com.kakao.cafe.dto.QuestionCreateRequest;

import java.time.LocalDateTime;

public class Question {
    private Long id;
    private String writer;
    private String title;
    private String contents;
    private LocalDateTime createdDateTime;

    public Question(){
        createdDateTime = LocalDateTime.now();
    }

    public Question(QuestionCreateRequest questionCreateRequest) {
        createdDateTime = LocalDateTime.now();
        this.writer = questionCreateRequest.getWriter();
        this.title = questionCreateRequest.getTitle();
        this.contents = questionCreateRequest.getContents();
    }

    public Long getId() {
        return id;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
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

    public void setId(Long id) {
        this.id = id;
    }
}
