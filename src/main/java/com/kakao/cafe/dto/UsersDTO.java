package com.kakao.cafe.dto;

import com.kakao.cafe.domain.User;

import java.util.List;
import java.util.stream.Collectors;

public class UsersDTO {
    private final List<UserDTO> users;

    public UsersDTO(List<User> users) {
        this.users = users.stream()
                          .map(UserDTO::new)
                          .collect(Collectors.toList());
    }

    public List<UserDTO> getUsers() {
        return this.users;
    }
}
