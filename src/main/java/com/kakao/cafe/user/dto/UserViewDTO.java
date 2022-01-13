package com.kakao.cafe.user.dto;

import com.kakao.cafe.user.domain.User;


public class UserViewDTO {

    private final Long id;

    private final String userId;

    private final String name;

    private final String email;

    public UserViewDTO(User user) {
        this.id = user.getId();
        this.userId = user.getUserId();
        this.name = user.getName();
        this.email = user.getEmail();
    }
}
