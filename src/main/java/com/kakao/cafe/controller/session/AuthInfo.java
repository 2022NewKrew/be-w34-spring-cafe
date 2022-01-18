package com.kakao.cafe.controller.session;

import lombok.Builder;
import lombok.Getter;

@Getter
public class AuthInfo {
    private Long id;
    private String stringId;
    private String name;

    @Builder
    private AuthInfo(Long id, String stringId, String name) {
        this.id = id;
        this.stringId = stringId;
        this.name = name;
    }
}
