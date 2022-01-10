package com.kakao.cafe.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class User {

    private final String userId;
    private final String password;
    private final String name;
    private final String email;
}
