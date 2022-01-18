package com.kakao.cafe.user.service.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class UserProfileServiceResponse {
    private String stringId;
    private String name;
    private String email;

    @Builder
    private UserProfileServiceResponse(String stringId, String name, String email) {
        this.stringId = stringId;
        this.name = name;
        this.email = email;
    }
}
