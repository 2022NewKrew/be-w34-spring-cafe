package com.kakao.cafe.user;

public class Users {
    private Long id;
    private String userId;
    private String password;

    public Users(String userId, String password) {
        this.userId = userId;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
