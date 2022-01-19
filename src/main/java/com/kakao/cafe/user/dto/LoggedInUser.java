package com.kakao.cafe.user.dto;

public class LoggedInUser {
    private final Long id;
    private final String username;

    public LoggedInUser(Long id, String username) {
        this.id = id;
        this.username = username;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }
}
