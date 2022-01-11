package com.kakao.cafe.controller.users.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@RequiredArgsConstructor
@Getter
@ToString
public class UserInfoDto {
    private final Long id;
    private final String userId;
    private final String userName;
    private final String email;
}
