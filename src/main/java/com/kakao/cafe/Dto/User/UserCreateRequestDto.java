package com.kakao.cafe.Dto.User;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@RequiredArgsConstructor
public class UserCreateRequestDto {
    private final String userId;
    private final String nickname;
    private final String email;
    private final String password;
}
