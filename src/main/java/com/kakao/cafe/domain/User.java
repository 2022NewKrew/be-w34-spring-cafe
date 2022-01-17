package com.kakao.cafe.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class User {
    private int id;
    private String userId;
    private String email;
    private String name;
    private String password;
}
