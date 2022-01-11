package com.kakao.cafe.controller.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserSignUpRequestDto {
    private final String userId;
    private final String password;
    private final String name;
    private final String email;

}
