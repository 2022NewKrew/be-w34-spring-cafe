package com.kakao.cafe.vo;

import com.kakao.cafe.model.NamedEntity;

public class User extends NamedEntity {

    private String userId;
    private String password;
    private String email;
    private String profileImage;

    public User(String userId, String password, String email) {
        this.userId = userId;
        this.password = password;
        this.email = email;
    }

    public String getUserId() {
        return userId;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }
}
