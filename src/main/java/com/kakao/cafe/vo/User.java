package com.kakao.cafe.vo;

import com.kakao.cafe.model.NamedEntity;

public class User extends NamedEntity {
    private String userId;
    private String password;
    private String email;
    private String profileImage;

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
