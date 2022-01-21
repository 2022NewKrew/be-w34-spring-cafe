package com.kakao.cafe.entity;

import java.util.Objects;

public class User {

    private final Integer id;
    private final String userId;
    private final String password;
    private final String email;

    public User(Integer id, String userId, String password, String email) {
        this.id = id;
        this.userId = userId;
        this.password = password;
        this.email = email;
    }

    public Integer getId() {
        return id;
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
