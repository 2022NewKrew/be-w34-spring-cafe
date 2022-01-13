package com.kakao.cafe.user.dto;


import lombok.Getter;


@Getter
public class SignUpDTO {

    private final String userId;

    private final String password;

    private final String name;

    private final String email;

    public SignUpDTO(String userId, String password, String name, String email) {
        this.userId = userId;
        this.password = password;
        this.name = name;
        this.email = email;
    }

}
