package com.kakao.cafe.user.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@RequiredArgsConstructor
@Getter
@ToString
public class UserView {
    private final String username;
    private final String email;
    private final String displayName;
}
