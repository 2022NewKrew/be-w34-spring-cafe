package com.kakao.cafe.dto;

import lombok.Getter;

@Getter
public class UserProfileDto {
    private final String id;
    private final String name;
    private final String email;

    public UserProfileDto(String id, String name, String email) {
        validate(id, name, email);
        this.id = id;
        this.name = name;
        this.email = email;
    }
    private void validate(String id, String name, String email) {

    }
}
