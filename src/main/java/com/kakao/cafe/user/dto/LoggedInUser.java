package com.kakao.cafe.user.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@RequiredArgsConstructor
@Getter
@ToString
public class LoggedInUser {
    private final Long id;
    private final String username;
}
