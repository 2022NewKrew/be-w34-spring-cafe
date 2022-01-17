package com.kakao.cafe.controller.user.dto;

public class UserUpdateDto {

    private final String name;
    private final String email;

    public UserUpdateDto(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}
