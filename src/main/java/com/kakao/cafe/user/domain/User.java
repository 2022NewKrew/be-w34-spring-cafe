package com.kakao.cafe.user.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class User {

    private Long id;
    private String email;
    private String password;
    private String username;

    public void setId(Long id) {
        this.id = id;
    }
}
