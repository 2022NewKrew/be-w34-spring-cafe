package com.kakao.cafe.domain.user;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class User {

    private Long userId;
    private String password;
    private String name;
    private String email;
}
