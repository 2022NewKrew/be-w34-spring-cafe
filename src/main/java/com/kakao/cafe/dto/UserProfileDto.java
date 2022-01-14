package com.kakao.cafe.dto;

import java.util.UUID;

public class UserProfileDto {
    private final UUID id;
    private final String name;
    private final String email;

    public UserProfileDto(UUID id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }
}
