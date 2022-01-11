package com.kakao.cafe.domain;

public class Users {
    private Long id;
    private String userId;
    private String password;
    private String name;
    private String email;

    public Users(String userId, String password, String name, String email) {
        this.userId = userId;
        this.password = password;
        this.name = name;
        this.email = email;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

}
