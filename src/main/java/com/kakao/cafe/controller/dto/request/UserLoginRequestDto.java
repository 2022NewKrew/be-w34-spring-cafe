package com.kakao.cafe.controller.dto.request;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class UserLoginRequestDto {

    private final String userId;
    private final String password;

}
