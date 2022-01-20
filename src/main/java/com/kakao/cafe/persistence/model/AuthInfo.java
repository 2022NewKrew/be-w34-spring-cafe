package com.kakao.cafe.persistence.model;

import javax.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class AuthInfo {

    @NotBlank
    private final String uid;

    public static AuthInfo of(String uid) {
        return new AuthInfo(uid);
    }

    private AuthInfo(String uid) {
        this.uid = uid;
    }

    public boolean matchUid(String uid) {
        return this.uid.equals(uid);
    }
}
