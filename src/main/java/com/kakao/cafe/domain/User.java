package com.kakao.cafe.domain;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class User {
    private Long id;
    private final String userId;
    private final String password;
    private final String name;
    private final String email;

    public void setId(Long id) {
        this.id = id;
    }
}
