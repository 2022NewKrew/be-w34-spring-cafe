package com.kakao.cafe.user.presentation.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class JoinRequest {
    private final String userId;
    private final String password;
    private final String name;
    private final String email;
}
