package com.kakao.cafe.user.domain;

public class User {

    private final UserId userId;
    private final Email email;
    private final String nickname;
    private final String password;

    public User(UserId userId, Email email, String nickname, String password) {
        this.userId = userId;
        this.email = email;
        this.nickname = nickname;
        this.password = password;
    }

    public UserId getUserId() {
        return userId;
    }

    public Email getEmail() {
        return email;
    }

    public String getNickname() {
        return nickname;
    }

    public String getPassword() {
        return password;
    }
}
