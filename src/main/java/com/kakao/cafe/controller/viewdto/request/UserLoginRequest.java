package com.kakao.cafe.controller.viewdto.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class UserLoginRequest {
    private final String stringId;
    private final String password;
}
