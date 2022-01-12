package com.kakao.cafe.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@RequiredArgsConstructor
@Getter
@ToString
public class UserCreateRequestDto {
    private final String userId;
    private final String nickname;
    private final String email;
    private final String password;

    public User toUser() {
        return new User(this);
    }
}
