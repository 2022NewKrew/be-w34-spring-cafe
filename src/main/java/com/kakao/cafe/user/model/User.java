package com.kakao.cafe.user.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class User {
    private final Long id;
    private final String userId;
    private final String name;
    private final String password;
    private final String email;
}
