package com.kakao.cafe.user.dto;

import com.kakao.cafe.user.domain.User;

public class ProfileViewDTO {

    private final String name;

    private final String email;

    public ProfileViewDTO(User user) {
        this.name = user.getName();
        this.email = user.getEmail();
    }
}
