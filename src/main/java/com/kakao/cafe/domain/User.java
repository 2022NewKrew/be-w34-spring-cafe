package com.kakao.cafe.domain;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class User {
    private Long id;
    private String userId;
    private String password;
    private String name;
    private String email;

    public void setId(Long id) {
        this.id = id;
    }

    public void update(String updateName, String updatePassword, String updateEmail) {
        name = updateName;
        password = updatePassword;
        email = updateEmail;
    }

    public boolean isCorrectPassword(String password) {
        return this.password.equals(password);
    }
}
