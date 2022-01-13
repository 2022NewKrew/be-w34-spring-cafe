package com.kakao.cafe.dto;

import com.kakao.cafe.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserSignUpDto {
    private String userId;
    private String email;
    private String name;
    private String password;

    public User toEntity() {
        return new User(userId, email, name, password);
    }
}
