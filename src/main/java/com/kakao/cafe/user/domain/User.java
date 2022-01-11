package com.kakao.cafe.user.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class User {

    private Long id;
    private String email;
    private String username;
    private String password;

    public void setId(Long id) {
        this.id = id;
    }
}
