package com.kakao.cafe.domain;

public class UserInfo {
    private String signUpDate;
    private String password;
    private String name;
    private String email;

    public UserInfo(String password, String name, String email) {
        this.password = password;
        this.name = name;
        this.email = email;
        this.signUpDate = TimeGenerator.todayDate();
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "singUpDate='" + signUpDate + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
