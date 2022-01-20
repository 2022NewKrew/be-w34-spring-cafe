package com.kakao.cafe.application.article.dto;

public class WriteRequest {

    private final String title;
    private final String contents;
    private String userId;
    private String writer;

    public WriteRequest(String title, String contents) {
        this.title = title;
        this.contents = contents;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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

    public String getContents() {
        return contents;
    }
}
