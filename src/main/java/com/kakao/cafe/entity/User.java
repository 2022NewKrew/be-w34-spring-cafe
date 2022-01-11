package com.kakao.cafe.entity;

import java.time.LocalDateTime;

public class User extends BaseEntity {
    private final String nickname;
    private String email;
    private String name;
    private String password;

    public User(String nickname, String email, String name, String password, LocalDateTime createdTime) {
        super(createdTime);
        this.nickname = nickname;
        this.email = email;
        this.name = name;
        this.password = password;
    }

    public Long getUserId() {
        return id;
    }

    public String getNickname() {
        return nickname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
