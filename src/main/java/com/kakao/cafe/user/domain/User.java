package com.kakao.cafe.user.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class User {
    private final UserId userId;
    private Password password;
    private Name name;
    private Email email;
}
