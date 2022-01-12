package com.kakao.cafe.user.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UserUpdateRequest {
    private final String passwordCheck;
    private final String newPassword;
    private final String name;
    private final String email;
}
