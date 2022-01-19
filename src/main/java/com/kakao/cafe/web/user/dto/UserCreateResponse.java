package com.kakao.cafe.web.user.dto;

import com.kakao.cafe.domain.user.Email;
import com.kakao.cafe.domain.user.User;
import com.kakao.cafe.domain.user.UserId;

public class UserCreateResponse {
    private final UserId userId;
    private final Email email;

    public UserCreateResponse(User user) {
        this.userId = user.getUserId();
        this.email = user.getEmail();
    }

    public Email getEmail() {
        return email;
    }

    public UserId getUserId() {
        return userId;
    }
}
