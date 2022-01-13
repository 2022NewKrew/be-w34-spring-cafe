package com.kakao.cafe.controller.viewdto.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class UserUpdateRequest {
    private final String oldPassword;
    private final String newPassword;
    private final String name;
    private final String email;
    private final String userId;
}
