package com.kakao.cafe.user.dto;

import lombok.Getter;

@Getter
public class UpdateDTO {

    private Long id;

    private String userId;

    private String password;

    private String newPassword;

    private String name;

    private String email;

    public UpdateDTO(Long id, String userId, String password, String newPassword, String name, String email) {
        this.id = id;
        this.userId = userId;
        this.password = password;
        this.newPassword = newPassword;
        this.name = name;
        this.email = email;
    }
}
