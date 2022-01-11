package com.kakao.cafe.user.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Setter
@Getter
public class User {

    private String userId;
    private String password;
    private String name;
    private String email;
}
