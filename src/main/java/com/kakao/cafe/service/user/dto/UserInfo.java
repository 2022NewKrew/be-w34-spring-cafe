package com.kakao.cafe.service.user.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class UserInfo {
    private final String userId;
    private final String userName;
    private final String email;

    @Builder
    public UserInfo(String userId, String userName, String email) {
        this.userId = userId;
        this.userName = userName;
        this.email = email;
    }
}
