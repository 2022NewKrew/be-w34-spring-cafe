package com.kakao.cafe.dto;

import lombok.Getter;

@Getter
public class UserFormDto {

    private final String username;
    private final String password;
    private final String name;
    private final String email;

    public UserFormDto(String username, String password, String name, String email) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.email = email;
    }

    @Override
    public String toString() {
        return "UserFormDto{" +
            "username='" + username + '\'' +
            ", password='" + password + '\'' +
            ", name='" + name + '\'' +
            ", email='" + email + '\'' +
            '}';
    }
}
