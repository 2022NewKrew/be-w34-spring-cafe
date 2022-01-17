package com.kakao.cafe.service.user.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class UserSignUpForm {
    private final String userId;
    private final String password;
    private final String userName;
    private final String email;

    @Builder
    public UserSignUpForm(String userId, String password, String userName, String email) {
        this.userId = userId;
        this.password = password;
        this.userName = userName;
        this.email = email;
    }
}
