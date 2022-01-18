package com.kakao.cafe.dto;

public class EditUserDto {
    private final String name;
    private final String email;

    public EditUserDto(String name, String email) {
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
