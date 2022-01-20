package com.kakao.cafe.domain.entity;

import java.util.Map;

public class ModifyUser {

    private final String name;
    private final String email;

    public ModifyUser(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public Map<String, Object> toMap() {
        return Map.of(
                "name", name,
                "email", email
        );
    }
}
