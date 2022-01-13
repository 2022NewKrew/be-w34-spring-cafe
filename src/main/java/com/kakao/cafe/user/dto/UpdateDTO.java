package com.kakao.cafe.user.dto;

import lombok.Getter;

@Getter
public class UpdateDTO {

    private final Long id;

    private final String userId;

    private final String password;

    private final String newPassword;

    private final String name;

    private final String email;

    public UpdateDTO(Long id, String userId, String password, String newPassword, String name, String email) {
        this.id = id;
        this.userId = userId;
        this.password = password;
        this.newPassword = newPassword;
        this.name = name;
        this.email = email;
    }
}
