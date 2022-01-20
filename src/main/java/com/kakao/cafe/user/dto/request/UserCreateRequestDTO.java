package com.kakao.cafe.user.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

import com.kakao.cafe.user.entity.User;

@AllArgsConstructor
@Getter
public class UserCreateRequestDTO {
    private final String userId;
    private final String password;
    private final String name;
    private final String email;

    public User toEntity() {
        return User.builder()
                   .userId(userId)
                   .password(password)
                   .name(name)
                   .email(email)
                   .build();
    }
}
