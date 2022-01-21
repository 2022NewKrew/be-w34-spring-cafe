package com.kakao.cafe.dto;

import com.kakao.cafe.entity.User;

import javax.validation.constraints.NotBlank;


public class UserRegistrationDto {
    @NotBlank(message = "닉네임을 입력해주세요")
    private final String userId;
    @NotBlank(message = "패스워드를 입력해주세요")
    private final String password;
    @NotBlank(message = "이메일을 입력해주세요")
    private final String email;
//    private final String profileImage;

    public User toEntity() {
        return new User(0, userId, password, email);
    }

    public UserRegistrationDto(String userId, String password, String email) {
        this.userId = userId;
        this.password = password;
        this.email = email;
    }

    public String getUserId() {
        return userId;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

//    public String getProfileImage() {
//        return profileImage;
//    }

//    public void setUserId(String userId) {
//        this.userId = userId;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//    public void setProfileImage(String profileImage) {
//        this.profileImage = profileImage;
//    }
}
