package com.kakao.cafe.user.dto;

import lombok.Getter;

@Getter
public class UpdateDTO {

    private Long id;

    private String userId;

    private String password;

    private String name;

    private String email;

    public UpdateDTO(Long id, String userId, String password, String name, String email) {
        this.id = id;
        this.userId = userId;
        this.password = password;
        this.name = name;
        this.email = email;
    }
}
