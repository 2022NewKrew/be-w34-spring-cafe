package com.kakao.cafe.module.model.domain;

import lombok.*;

@Getter
@AllArgsConstructor
public class User {

    private final Long sn;
    private final String userId;
    private final String password;
    private final String name;
    private final String email;
}
