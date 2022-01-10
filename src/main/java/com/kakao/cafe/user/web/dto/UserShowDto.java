package com.kakao.cafe.user.web.dto;

import lombok.Builder;

@Builder
public class UserShowDto {

    private String userId;
    private String name;
    private String email;
}
