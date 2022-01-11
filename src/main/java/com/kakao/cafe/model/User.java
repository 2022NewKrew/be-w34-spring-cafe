package com.kakao.cafe.model;

public class User {
    private static long offset = 0;
    private final long id;
    private final String userId;
    private final String password;
    private final String nickname;
    private final String email;

    public User(String userId, String password, String nickname, String email) {
        offset += 1;
        this.id = offset;
        this.userId = userId;
        this.password = password;
        this.nickname = nickname;
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId='" + userId + '\'' +
                ", password='" + password + '\'' +
                ", nickname='" + nickname + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    public String getNickname() {
        return nickname;
    }

    public long getId() {
        return id;
    }
}
