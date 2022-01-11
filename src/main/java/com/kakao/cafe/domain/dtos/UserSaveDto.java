package com.kakao.cafe.domain.dtos;

public class UserSaveDto {
    private final String email;
    private final String name;
    private final String password;

    public UserSaveDto(String email, String name, String password) {
        this.email = email;
        this.name = name;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }
}
