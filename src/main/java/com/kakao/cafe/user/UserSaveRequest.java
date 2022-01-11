package com.kakao.cafe.user;

import java.io.Serializable;

public class UserSaveRequest implements Serializable {
    private String userId;
    private String password;
    private String name;
    private String email;

    public User toUser() {
        System.out.println(userId);
        return new User(
                this.userId,
                this.password,
                this.name,
                this.email
        );
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
