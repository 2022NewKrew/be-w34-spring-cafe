package com.kakao.cafe.service.dto;

import com.kakao.cafe.domain.entity.SignUp;

public class SignUpDto {

    private final String userId;
    private final String password;
    private final String name;
    private final String email;

    public SignUpDto(String userId, String password, String name, String email) {
        this.userId = userId;
        this.password = password;
        this.name = name;
        this.email = email;
    }

    public SignUp toEntity() {
        return new SignUp(userId, password, name, email);
    }
}
