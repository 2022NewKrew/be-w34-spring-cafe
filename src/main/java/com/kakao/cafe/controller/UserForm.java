package com.kakao.cafe.controller;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserForm {
    private String userId;
    private int password;
    private String name;
    private String email;
}
