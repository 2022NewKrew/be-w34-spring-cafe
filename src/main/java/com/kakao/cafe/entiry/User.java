package com.kakao.cafe.entiry;

import com.kakao.cafe.dto.UserForm;

import java.time.LocalDateTime;

public class User {

    private Long id;
    private String userId;
    private String password;
    private String name;
    private String email;
    private LocalDateTime joinDateTime;

    public User() {}

    public User(String userId, String password, String name, String email) {
        this.userId = userId;
        this.password = password;
        this.name = name;
        this.email = email;
        this.joinDateTime = LocalDateTime.now();
    }

    public User(Long id, String userId, String password, String name, String email, LocalDateTime joinDateTime) {
        this.id = id;
        this.userId = userId;
        this.password = password;
        this.name = name;
        this.email = email;
        this.joinDateTime = joinDateTime;
    }

    public static User of(UserForm userForm){
        return new User(userForm.getUserId(), userForm.getPassword(), userForm.getName(), userForm.getEmail());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDateTime getJoinDateTime() {
        return joinDateTime;
    }

    public void setJoinDateTime(LocalDateTime joinDateTime) {
        this.joinDateTime = joinDateTime;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userId='" + userId + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", joinDateTime=" + joinDateTime +
                '}';
    }
}
