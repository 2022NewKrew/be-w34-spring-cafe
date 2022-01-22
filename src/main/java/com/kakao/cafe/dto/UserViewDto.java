package com.kakao.cafe.dto;

import lombok.Getter;

@Getter
public class UserViewDto {

    private final String id;
    private final String username;
    private final String name;
    private final String email;

    public UserViewDto(String id, String username, String name, String email) {
        this.id = id;
        this.username = username;
        this.name = name;
        this.email = email;
    }

    @Override
    public String toString() {
        return "UserViewDto{" +
            "id='" + id + '\'' +
            ", username='" + username + '\'' +
            ", name='" + name + '\'' +
            ", email='" + email + '\'' +
            '}';
    }
}
