package com.kakao.cafe.web.dto.user;

import lombok.Getter;

@Getter
public class UserUpdateRequestDto {
    private final String prevAccPw;
    private final String newAccPw;
    private final String name;
    private final String email;

    public UserUpdateRequestDto(String prevAccPw, String newAccPw, String name, String email) {
        this.prevAccPw = prevAccPw;
        this.newAccPw = newAccPw;
        this.name = name;
        this.email = email;
    }
}
