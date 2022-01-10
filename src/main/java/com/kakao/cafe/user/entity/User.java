package com.kakao.cafe.user.entity;

import com.kakao.cafe.user.dto.request.UserCreateRequest;

public class User {
    private Integer id;
    private String userId;
    private String password;
    private String name;
    private String email;

    public User() {}

    public User(UserCreateRequest req) {
        this(req.getUserId(),
             req.getPassword(),
             req.getName(),
             req.getEmail());
    }

    public User(String userId, String password, String name, String email) {
        this.userId = userId;
        this.password = password;
        this.name = name;
        this.email = email;
    }

    public Integer getId() { return this.id; }
    public String getUserId() { return this.userId; }
    public String getPassword() { return this.password; }
    public String getName() { return this.name; }
    public String getEmail() { return this.email; }

    public void setId(Integer id) { this.id = id; }
    public void setUserId(String userId) { this.userId = userId; }
    public void setPassword(String password) { this.password = password; }
    public void setName(String name) { this.name = name; }
    public void setEmail(String email) { this.email = email; }
}
