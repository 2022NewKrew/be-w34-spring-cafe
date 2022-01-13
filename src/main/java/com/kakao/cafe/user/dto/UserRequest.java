package com.kakao.cafe.user.dto;


import com.kakao.cafe.user.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Getter
@AllArgsConstructor
public class UserRequest {
    @NotNull
    private final String userId;

    @NotNull
    private final String name;

    @NotNull
    private final String password;

    @Email
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
