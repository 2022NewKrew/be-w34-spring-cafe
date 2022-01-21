package com.kakao.cafe.dto;

import lombok.Getter;

@Getter
public class UserNoPwdDto {

    private final String id;
    private final String username;
    private final String name;
    private final String email;

    public UserNoPwdDto(String id, String username, String name, String email) {
        this.id = id;
        this.username = username;
        this.name = name;
        this.email = email;
    }

    @Override
    public String toString() {
        return "UserNoPwdDto{" +
            "id='" + id + '\'' +
            ", username='" + username + '\'' +
            ", name='" + name + '\'' +
            ", email='" + email + '\'' +
            '}';
    }
}
