package com.kakao.cafe.web.domain;

public class User {
    String userId;
    String password;
    String name;
    String email;

    public User(String userId, String password, String name, String email) {
        this.userId = userId;
        this.password = password;
        this.name = name;
        this.email = email;
    }
}
