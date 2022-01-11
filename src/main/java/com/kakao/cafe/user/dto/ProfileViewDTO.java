package com.kakao.cafe.user.dto;

import com.kakao.cafe.user.domain.User;

public class ProfileViewDTO {

    private String name;

    private String email;

    public ProfileViewDTO(User user) {
        this.name = user.getName();
        this.email = user.getEmail();
    }
}
