package com.kakao.cafe.domain;

import lombok.Data;

@Data
public class User {
    private Long id;
    private String userId;
    private String password;
    private String name;
    private String email;
}
