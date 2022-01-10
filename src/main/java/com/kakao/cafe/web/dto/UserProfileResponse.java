package com.kakao.cafe.web.dto;

import com.kakao.cafe.domain.user.User;

import java.util.Optional;

public class UserProfileResponse {
    private String name;
    private String email;

    public UserProfileResponse(Optional<User> userVO) {
        this.name = userVO.get().getName();
        this.email = userVO.get().getEmail();
    }

    public User toEntity() {
        User user = new User();

        user.setName(name);
        user.setEmail(email);

        return user;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
