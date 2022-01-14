package com.kakao.cafe.model;

import lombok.Getter;

@Getter
public class User {
    private String userId;
    private String password;
    private String name;
    private String email;

    public User(UserSignUpDTO userSignUpDTO) {
        this.userId = userSignUpDTO.getUserId();
        this.password = userSignUpDTO.getPassword();
        this.name = userSignUpDTO.getName();
        this.email = userSignUpDTO.getEmail();
    }
}



