package com.kakao.cafe.dto;

public class UserValidateAjaxForm {

    private String password;

    public UserValidateAjaxForm(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
