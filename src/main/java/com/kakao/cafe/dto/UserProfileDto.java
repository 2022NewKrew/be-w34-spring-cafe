package com.kakao.cafe.dto;

import com.kakao.cafe.model.User;
import java.util.UUID;

public class UserProfileDto {

    private final UUID id;
    private final String name;
    private final String email;

    private UserProfileDto(UUID id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public static UserProfileDto of(User user) {
        return new UserProfileDto(
                user.getId(),
                user.getName(),
                user.getEmail());
    }
}
