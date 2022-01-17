package com.kakao.cafe.model;

import lombok.Builder;

public class User {
    private Integer id;
    private String userId;
    private String password;
    private String userName;
    private String email;

    @Builder
    public User(int id, String userId, String password, String userName, String email) {
        this.id = id;
        this.userId = userId;
        this.password = password;
        this.userName = userName;
        this.email = email;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() { return id; }

    public String getUserId() {
        return userId;
    }

    public String getPassword() {
        return password;
    }

    public String getUserName() {
        return userName;
    }

    public String getEmail() {
        return email;
    }

    public void validate(){

    }

    public boolean matchPassword(String password){
        return this.password.equals(password);
    }

    @Override
    public String toString() {
        return "User{" +
                "userId='" + userId + '\'' +
                ", password='" + password + '\'' +
                ", userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
