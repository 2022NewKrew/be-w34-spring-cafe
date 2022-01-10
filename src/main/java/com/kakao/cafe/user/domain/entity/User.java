package com.kakao.cafe.user.domain.entity;


import com.kakao.cafe.util.ValidationService;
import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
public class User {
    @NotNull
    @Size(min = 3, max = 10)
    private final String userId;

    @NotNull
    @Size(min = 8, max = 16)
    private String password;

    @NotNull
    @Size(min = 3, max = 5)
    private String name;

    @NotNull
    @Email
    private String email;


    public User(String userId, String password, String name, String email) {
        this.userId = userId;
        this.password = password;
        this.name = name;
        this.email = email;
        ValidationService.validate(this);
    }
}
