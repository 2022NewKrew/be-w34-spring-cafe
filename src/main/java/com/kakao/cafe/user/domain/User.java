package com.kakao.cafe.user.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {
    @Setter(AccessLevel.NONE)
    private final String userId;

    @Getter(AccessLevel.NONE)
    private String password;

    private String name;
    private String email;
    private String pictureAddress;

    private static final String DUMMY_ADDRESS = "/images/80-text.png";

    public User(String userId, String password, String name, String email) {
        this.userId = userId;
        this.password = password;
        this.name = name;
        this.email = email;
        this.pictureAddress = DUMMY_ADDRESS;
    }
}
