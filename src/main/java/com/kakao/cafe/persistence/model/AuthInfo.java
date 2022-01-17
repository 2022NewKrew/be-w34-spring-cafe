package com.kakao.cafe.persistence.model;

import lombok.Getter;

@Getter
public class AuthInfo {

    private final String uid;

    public static AuthInfo of(String uid) {
        return new AuthInfo(uid);
    }

    private AuthInfo(String uid) {
        this.uid = uid;
    }
}
