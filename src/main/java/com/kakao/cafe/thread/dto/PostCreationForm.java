package com.kakao.cafe.thread.dto;

public class PostCreationForm {
    private final String author_username;
    private final String title;
    private final String content;

    public String getAuthor_username() {
        return author_username;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public PostCreationForm(String author_username, String title, String content) {
        this.author_username = author_username;
        this.title = title;
        this.content = content;
    }
}
