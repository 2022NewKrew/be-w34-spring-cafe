package com.kakao.cafe.controller.users.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@ToString
public class UserSignUpRequest {
    private final String userId;
    private final String password;
    private final String name;
    private final String email;
}
