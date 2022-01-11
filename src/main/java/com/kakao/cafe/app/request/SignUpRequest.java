package com.kakao.cafe.app.request;

import com.kakao.cafe.service.dto.SignUpDto;

public class SignUpRequest {

    private final String userId;
    private final String password;
    private final String name;
    private final String email;

    public SignUpRequest(
            String userId,
            String password,
            String name,
            String email
    ) {
        this.userId = userId;
        this.password = password;
        this.name = name;
        this.email = email;
    }

    public SignUpDto toSignUpDto() {
        return new SignUpDto(userId, password, name, email);
    }
}
