package com.kakao.cafe.controller.users.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class UserUpdateFormResponse {
    private final String userId;
    private final String userName;
    private final String email;

    @Builder
    public UserUpdateFormResponse(String userId, String userName, String email) {
        this.userId = userId;
        this.userName = userName;
        this.email = email;
    }
}
