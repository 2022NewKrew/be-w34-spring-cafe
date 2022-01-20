package com.kakao.cafe.interfaces.user.dto.response;

import com.kakao.cafe.domain.user.User;

public class LoginInfoDto {
    private final String userId;

    public LoginInfoDto(User user) {
        this.userId = user.getUserId();
    }

    public String getUserId() {
        return userId;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
