package com.kakao.cafe.domain;

import lombok.Getter;
import lombok.Setter;

public class User {
    @Getter
    private final String userId;
    @Setter
    private String password;
    @Getter @Setter
    private String name;
    @Getter @Setter
    private String email;
    @Getter @Setter
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
