package com.kakao.cafe.user;

public class UserSaveRequest {
    private String userId;
    private String password;
    private String name;
    private String email;

    public User toUser() {
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
