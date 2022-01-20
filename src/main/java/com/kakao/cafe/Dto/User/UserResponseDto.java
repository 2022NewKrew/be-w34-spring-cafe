package com.kakao.cafe.Dto.User;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserResponseDto {
    private final int id;
    private final String userId;
    private final String nickname;
    private final String email;
}
