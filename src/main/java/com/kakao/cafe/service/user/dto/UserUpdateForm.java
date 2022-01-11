package com.kakao.cafe.service.user.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@RequiredArgsConstructor
@Builder
@Getter
@ToString
public class UserUpdateForm {
    private final Long id;
    private final String userId;
    private final String password;
    private final String userName;
    private final String email;
}
