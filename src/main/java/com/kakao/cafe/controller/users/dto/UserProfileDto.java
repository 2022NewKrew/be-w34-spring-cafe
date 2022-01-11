package com.kakao.cafe.controller.users.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@RequiredArgsConstructor
@Getter
@ToString
public class UserProfileDto {
    private final String userName;
    private final String email;
}
