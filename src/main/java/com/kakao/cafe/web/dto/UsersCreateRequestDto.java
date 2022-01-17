package com.kakao.cafe.web.dto;

import com.kakao.cafe.domain.users.UserEntity;

public class UsersCreateRequestDto {

    private String password;
    private String name;
    private String email;

    public UserEntity toEntity() {
        return new UserEntity(this.name, this.email, this.password);
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

    public boolean checkUserInfo (String email, String password) {
        return email.equals(this.email) && password.equals(this.password);
    }

}
