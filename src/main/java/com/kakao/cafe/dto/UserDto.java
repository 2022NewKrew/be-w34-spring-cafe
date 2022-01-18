package com.kakao.cafe.dto;

import com.kakao.cafe.entity.User;

import java.time.LocalDate;

public class UserDto {
    private final int userId;
    private final String password;
    private final String name;
    private final String email;
    private final LocalDate createdTime;

    public UserDto(int id, String password, String name, String email, LocalDate createdTime) {
        this.userId = id;
        this.password = password;
        this.name = name;
        this.email = email;
        this.createdTime = createdTime;
    }

    public static UserDto entityToDto(User user) {
        return new UserDto(
                user.getId(),
                user.getPassword(),
                user.getName(),
                user.getEmail(),
                user.getCreatedTime()
        );
    }

    public String getPassword() {
        return password;
    }
}
