package com.kakao.cafe.user.dto;

import com.kakao.cafe.user.domain.User;
import lombok.Getter;

@Getter
public class UserViewDTO {

    private int id;

    private String userId;

    private String name;

    private String email;

    public UserViewDTO(User user) {
        this.id = user.getId();
        this.userId = user.getUserId();
        this.name = user.getName();
        this.email = user.getEmail();
    }
}
