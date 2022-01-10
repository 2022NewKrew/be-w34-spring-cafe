package com.kakao.cafe.user.model;

public class UserProfileDto {

    private final String name;
    private final String email;

    public UserProfileDto(String name, String email) {
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
