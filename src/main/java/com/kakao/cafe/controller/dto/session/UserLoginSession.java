package com.kakao.cafe.controller.dto.session;

import com.kakao.cafe.domain.User;
import lombok.Getter;

@Getter
public class UserLoginSession {

    private final String userId;
    private final String name;
    private final String email;

    public UserLoginSession(User user) {
        this.userId = user.getUserId();
        this.name = user.getName();
        this.email = user.getEmail();
    }
}
