package com.kakao.cafe.user.dto;

import javax.validation.constraints.NotEmpty;

public class UserUpdateDto {
    @NotEmpty
    private String userId;
    @NotEmpty
    private String name;
    @NotEmpty
    private String email;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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
}
