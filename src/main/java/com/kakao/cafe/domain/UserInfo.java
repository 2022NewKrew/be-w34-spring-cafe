package com.kakao.cafe.domain;

import org.apache.catalina.User;


public class UserInfo {
    private String userId;
    private String signUpDate;
    private String password;
    private String name;
    private String email;

    public UserInfo(String userId, String password, String name, String email) {
        this.userId = userId;
        this.password = password;
        this.name = name;
        this.email = email;
        this.signUpDate = TimeGenerator.todayDate();
    }


    public boolean hasEqualId(String otherId){
        return otherId.equals(this.userId);
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "userId='" + userId + '\'' +
                ", signUpDate='" + signUpDate + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
