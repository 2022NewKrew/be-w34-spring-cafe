package com.kakao.cafe.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class User {
    private String userId;
    private String password;
    private String name;
    private String email;
}



