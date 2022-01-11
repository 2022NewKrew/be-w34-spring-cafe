package com.kakao.cafe.dto;

public class SignupRequestDto {

    private final String userId;
    private final String password;
    private final String name;
    private final String email;

    public SignupRequestDto(String userId, String password, String name, String email) {
        this.userId = userId;
        this.password = password;
        this.name = name;
        this.email = email;
    }

    public String getUserId() {
        return userId;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}
