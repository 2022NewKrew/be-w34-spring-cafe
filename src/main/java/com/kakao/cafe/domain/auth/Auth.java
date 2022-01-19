package com.kakao.cafe.domain.auth;

public class Auth {

    private final Long authId;

    public Auth(Long authId) {
        this.authId = authId;
    }

    public boolean isNotValidId(Long id) {
        return !authId.equals(id);
    }
}
