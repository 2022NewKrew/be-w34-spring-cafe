package com.kakao.cafe.entity;

import java.util.Objects;

public class User {

    private Integer id;
    private final String userId;
    private final String password;
    private final String email;

    public User(String userId, String password, String email) {
        this.userId = userId;
        this.password = password;
        this.email = email;
    }

    public User(Integer id, String userId, String password, String email) {
        this.id = id;
        this.userId = userId;
        this.password = password;
        this.email = email;
    }

    public String getUserId() {
        return userId;
    }

    public String getEmail() { return email;}

    public String getPassword() { return password;}

    public boolean isEqualPassword(String inputPassword) {
        return Objects.equals(this.password, inputPassword);
    }
}
