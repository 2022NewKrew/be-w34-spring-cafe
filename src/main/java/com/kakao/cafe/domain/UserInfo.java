package com.kakao.cafe.domain;

public class UserInfo {
    String userId;
    String password;
    String name;
    String email;

    public UserInfo(String userId, String password, String name, String email) {
        this.userId= userId;
        this.password = password;
        this.name = name;
        this.email = email;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "id='" + userId + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

}
