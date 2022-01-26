package com.kakao.cafe.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.kakao.cafe.domain.User;

public class UsersDto {
    private final List<UserDto> users;

    public UsersDto(List<User> users) {
        this.users = users.stream()
                          .map(UserDto::new)
                          .collect(Collectors.toList());
    }

    public List<UserDto> getUsers() {
        return this.users;
    }
}
