package com.kakao.cafe.user.entity;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class User {


    private long Id;

    @NotNull
    private String userId;

    @NotNull
    private String password;

    @NotNull
    private String name;

    @NotNull
    @Email
    private String email;

    public User() {
    }

    public User(long id, String userId, String password, String name, String email) {
        Id = id;
        this.userId = userId;
        this.password = password;
        this.name = name;
        this.email = email;
    }
}
