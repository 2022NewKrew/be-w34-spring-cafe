package com.kakao.cafe.user.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
public class User {
    private final Long id;
    private final String email;
    private final String nickName;
    private final String password;
    private final LocalDateTime signUpDate;
}
