package com.kakao.cafe.dto;

public class CommentWebDto {
    private String name;
    private String userId;
    private String text;
    private long id;

    public CommentWebDto(String name, String userId, String text, long id) {
        this.name = name;
        this.userId = userId;
        this.text = text;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getUserId() {
        return userId;
    }

    public String getText() {
        return text;
    }

    public long getId() {
        return id;
    }
}
