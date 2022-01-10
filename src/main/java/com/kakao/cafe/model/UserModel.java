package com.kakao.cafe.model;

import com.kakao.cafe.domain.user.User;

public class UserModel {
    String name;
    String password;
    String userId;
    String email;

    public UserModel(){

    }

    public UserModel(String name, String password, String userId, String email){
        this.name = name;
        this.password = password;
        this.userId = userId;
        this.email = email;
    }


    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getUserId() {
        return userId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public static UserModel fromUser(User user){
        return new UserModel(user.getName(), user.getPassword(), user.getId(), user.getEmail());
    }


    @Override
    public String toString() {
        return "UserModel{" +
                "name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", userId='" + userId + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
