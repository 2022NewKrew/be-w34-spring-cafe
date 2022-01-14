package com.kakao.cafe.model;


import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
@Builder
public class UserViewDTO {
    private final String userId;
    private final String name;
    private final String email;
}

