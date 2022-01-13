package com.kakao.cafe.user.repository;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserUpdateRequestDTO {
    private final Long userId;
    private final String newPassword;
    private final String name;
    private final String email;
}
