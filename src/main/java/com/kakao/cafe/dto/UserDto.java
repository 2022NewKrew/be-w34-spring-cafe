package com.kakao.cafe.dto;

import com.kakao.cafe.entity.User;

import java.time.LocalDate;

public class UserDto {
    private int id;
    private String password;
    private String name;
    private String email;
    private LocalDate createdTime;

    public UserDto(int id, String password, String name, String email, LocalDate createdTime) {
        this.id = id;
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
}
