package com.kakao.cafe.domain;

public class User {

    private final Long id;
    private final String username;
    private final String nickname;
    private final String email;

    public User(Long id, String username, String nickname, String email) {
        this.id = id;
        this.username = username;
        this.nickname = nickname;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getNickname() {
        return nickname;
    }

    public String getEmail() {
        return email;
    }
}
