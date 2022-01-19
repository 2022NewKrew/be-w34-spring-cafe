package com.kakao.cafe.Dto.User;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserResponseDto {
    private final Long id;
    private final String userId;
    private final String nickname;
    private final String email;
}
