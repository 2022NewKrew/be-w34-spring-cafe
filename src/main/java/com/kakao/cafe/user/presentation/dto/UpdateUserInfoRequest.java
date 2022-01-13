package com.kakao.cafe.user.presentation.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UpdateUserInfoRequest {
    private final String name;
    private final String email;
}
