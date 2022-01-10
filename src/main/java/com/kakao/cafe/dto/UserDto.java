package com.kakao.cafe.dto;

import com.kakao.cafe.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserDto {

    private final String userId;
    private final String password;
    private final String name;
    private final String email;

    public User toEntity() {
        return new User(userId, password, name, email);
    }
}
