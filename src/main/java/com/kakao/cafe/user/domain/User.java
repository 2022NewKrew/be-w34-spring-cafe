package com.kakao.cafe.user.domain;

public class User {

    private final UserId userId;
    private final Email email;
    private final String nickname;
    private final Password password;

    public User(UserId userId, Email email, String nickname, Password password) {
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

    public Password getPassword() {
        return password;
    }
}
