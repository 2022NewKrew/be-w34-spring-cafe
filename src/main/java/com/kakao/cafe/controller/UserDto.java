package com.kakao.cafe.controller;

import com.kakao.cafe.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UserDto {

    private int id;
    private String userId;
    private int password;
    private String name;
    private String email;

    public UserDto(String userId, int password, String name, String email) {
        this.userId = userId;
        this.password = password;
        this.name = name;
        this.email = email;
    }

    public User toEntity() {
        return new User(userId, password, name, email);
    }

    public static UserDto from(User user) {
        UserDto userDto = new UserDto(user.getUserId(), user.getPassword(), user.getName(), user.getEmail());
        userDto.setId(user.getId());
        return userDto;
    }
}
