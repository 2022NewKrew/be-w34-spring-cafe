package com.kakao.cafe.domain;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class User {
    private String id;
    private String name;
    private String pw;
    private String email;

    private long key;
}