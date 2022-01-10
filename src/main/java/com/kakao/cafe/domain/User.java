package com.kakao.cafe.domain;

import lombok.Data;

@Data
public class User {
    private String userId;
    private String password;
    private String name;
    private String email;


    public User(String userId, String password, String name, String email) {
        this.userId = userId;
        this.password = password;
        this.name = name;
        this.email = email;
    }

}
