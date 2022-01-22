package com.kakao.cafe.domain.user;

import lombok.Data;

@Data
public class User {

    private Long userId;
    private String password;
    private String name;
    private String email;
}
