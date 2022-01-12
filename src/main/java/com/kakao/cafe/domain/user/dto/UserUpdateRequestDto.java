package com.kakao.cafe.domain.user.dto;

public class UserUpdateRequestDto {
    private String name;
    private String confirmPassword;
    private String newPassword;
    private String email;

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
