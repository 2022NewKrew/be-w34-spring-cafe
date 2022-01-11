package com.kakao.cafe.user.model;

import lombok.Getter;

@Getter
public class User {
    private final Long id;
    private final String userId;
    private final String name;
    private final String password;
    private final String email;

    public User(Long id, UserRequest userRequest) {
        this.id = id;
        this.userId = userRequest.getUserId();
        this.name = userRequest.getName();
        this.password = userRequest.getPassword();
        this.email = userRequest.getEmail();
    }

    public UserDto toUserDto(){
        return new UserDto(id, userId, name, email);
    }

    public UserProfileDto toProfileDto(){
        return new UserProfileDto(name, email);
    }
}
