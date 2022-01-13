package com.kakao.cafe.service.user.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@ToString
public class UserSignUpForm {
    private final String userId;
    private final String password;
    private final String userName;
    private final String email;
}
