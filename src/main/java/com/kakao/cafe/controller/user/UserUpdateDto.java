package com.kakao.cafe.controller.user;

public class UserUpdateDto {

    private final String password;
    private final String name;
    private final String email;

    public UserUpdateDto(String password, String name, String email) {
        this.password = password;
        this.name = name;
        this.email = email;
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
