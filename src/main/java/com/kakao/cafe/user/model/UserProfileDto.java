package com.kakao.cafe.user.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class UserProfileDto {
    private final String name;
    private final String email;
}
