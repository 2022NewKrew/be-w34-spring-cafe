package com.kakao.cafe.user.presentation.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ModifyUserInfoRequest {
    private final String password;
    private final String name;
    private final String email;
}
