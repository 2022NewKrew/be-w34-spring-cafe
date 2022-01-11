package com.kakao.cafe.user.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class UserRequest {
    private final String userId;
    private final String name;
    private final String password;
    private final String email;
}
