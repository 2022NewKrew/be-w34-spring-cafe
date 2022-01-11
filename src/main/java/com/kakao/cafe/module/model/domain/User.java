package com.kakao.cafe.module.model.domain;

import lombok.*;

@Getter
@ToString
@AllArgsConstructor
public class User {

    private final Long id;
    private final String userId;
    private String password;
    private String name;
    private String email;

    public void update(String password, String name, String email) {
        this.password = password;
        this.name = name;
        this.email = email;
    }
}
