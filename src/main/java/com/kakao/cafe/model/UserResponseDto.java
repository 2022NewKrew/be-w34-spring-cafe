package com.kakao.cafe.model;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserResponseDto {
    private final Long id;
    private final String userId;
    private final String nickname;
    private final String email;
}
