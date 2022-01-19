package com.kakao.cafe.Dto.User;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class UserCreateRequestDto {
    private final String userId;
    private final String nickname;
    private final String email;
    private final String password;

    public UserCreateRequestDto(String userId, String nickname, String email, String password) {
        this.userId = userId;
        this.nickname = nickname;
        this.email = email;
        this.password = password;
    }
}
