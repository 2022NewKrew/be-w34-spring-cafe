package com.kakao.cafe.user.model;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserRequest {
    private final String userId;
    private final String name;
    private final String password;
    private final String email;

    public User toEntity(Long id){
        return User.builder()
                .id(id)
                .userId(userId)
                .name(name)
                .password(password)
                .email(email)
                .build();
    }
}
