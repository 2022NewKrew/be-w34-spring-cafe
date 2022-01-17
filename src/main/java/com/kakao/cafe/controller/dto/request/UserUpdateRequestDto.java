package com.kakao.cafe.controller.dto.request;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserUpdateRequestDto {

    private final String name;
    private final String password;
    private final String email;
}
