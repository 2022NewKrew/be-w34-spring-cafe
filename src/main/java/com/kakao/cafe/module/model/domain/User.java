package com.kakao.cafe.module.model.domain;

import lombok.*;

@Getter
@ToString
@AllArgsConstructor
public class User {

    private final Long id;
    private final String userId;
    private final String password;
    private final String name;
    private final String email;
}
