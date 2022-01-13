package com.kakao.cafe.controller.viewdto.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class UserCreateRequest {
    private final String userId;
    private final String password;
    private final String name;
    private final String email;
}
