package com.kakao.cafe.application.article.dto;

public class UpdateArticleRequest {

    private final String title;
    private final String contents;
    private int id;
    private String userId;
    private String writer;

    public UpdateArticleRequest(String title, String contents) {
        this.title = title;
        this.contents = contents;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public String getContents() {
        return contents;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }
}
