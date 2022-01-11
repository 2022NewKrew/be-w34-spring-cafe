package com.kakao.cafe.controller;

import com.kakao.cafe.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
public class UserDto {

    private String userId;
    private int password;
    private String name;
    private String email;

    public User toEntity() {
        return new User(userId, password, name, email);
    }

    public static UserDto from(User user) {
        return new UserDto(user.getUserId(), user.getPassword(), user.getName(), user.getEmail());
    }
}
