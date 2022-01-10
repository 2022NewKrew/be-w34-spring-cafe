package com.kakao.cafe.domain;

public class Question {
    private Long id;
    private String writer;
    private String title;
    private String contents;

    public Question(QuestionCreateRequest questionCreateRequest) {
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
