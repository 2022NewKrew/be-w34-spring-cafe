package com.kakao.cafe.service.user.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@ToString
public class UserUpdateForm {
    private final String userId;
    private final String password;
    private final String userName;
    private final String email;

    @Builder
    public UserUpdateForm(String userId, String password, String userName, String email) {
        this.userId = userId;
        this.password = password;
        this.userName = userName;
        this.email = email;
    }
}
