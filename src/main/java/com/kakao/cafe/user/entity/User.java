package com.kakao.cafe.user.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {

    private long Id;

    private String userId;
    private String password;
    private String name;
    private String email;

    public User(long id, String userId, String password, String name, String email) {
        Id = id;
        this.userId = userId;
        this.password = password;
        this.name = name;
        this.email = email;
    }
}
