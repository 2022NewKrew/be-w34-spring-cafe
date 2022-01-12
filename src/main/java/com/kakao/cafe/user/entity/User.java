package com.kakao.cafe.user.entity;

import com.kakao.cafe.user.dto.request.UserCreateRequest;
import com.kakao.cafe.user.dto.request.UserUpdateRequest;

import java.time.LocalDateTime;

public class User {
    private Integer id;
    private String userId;
    private String password;
    private String name;
    private String email;
    private LocalDateTime createdAt;

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
    public LocalDateTime getCreatedAt() { return this.createdAt; }

    public void setId(Integer id) { this.id = id; }
    public void setUserId(String userId) { this.userId = userId; }
    public void setPassword(String password) { this.password = password; }
    public void setName(String name) { this.name = name; }
    public void setEmail(String email) { this.email = email; }
    public void setCreatedAt(LocalDateTime localDateTime) { this.createdAt = createdAt; }

    public void update(UserUpdateRequest req) {
        if(req.getNewPassword() != null) {
            this.setPassword(req.getNewPassword());
        }
        if(req.getName() != null) {
            this.setName(req.getName());
        }
        if(req.getEmail() != null) {
            this.setEmail(req.getEmail());
        }
    }
}
