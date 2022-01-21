package com.kakao.cafe.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {
    private String userId;
    private String password;
    private String email;
    private String name;
    private String createdAt;

    public User() {
    }
    public User(String userId, String email) {
        this.userId = userId;
        this.email = email;
    }
    public User(String userId, String email, String name, String createdAt) {
        this.userId = userId;
        this.email = email;
        this.name=name;
        this.createdAt = createdAt;
    }
}
