package com.kakao.cafe.domain.auth;

public class Auth {
    private final Long id;
    private final String name;

    public Auth(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
