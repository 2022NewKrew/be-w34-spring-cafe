package com.kakao.cafe.dto.user;

import lombok.Builder;

@Builder
public class UserResponseDto {
    private int id;
    private String stringId;
    private String name;
    private String email;
}
