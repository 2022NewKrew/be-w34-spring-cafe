package com.kakao.cafe.web.dto.user;

import lombok.Builder;
import lombok.Getter;

@Getter
public class UserResponseDto {
    private final String userId;
    private final String signUpDate;
    private final String name;
    private final String email;
    private final String password;

    @Builder
    public UserResponseDto(String userId, String signUpDate, String name, String email, String password) {
        this.userId = userId;
        this.signUpDate = signUpDate;
        this.name = name;
        this.email = email;
        this.password = password;
    }
}
