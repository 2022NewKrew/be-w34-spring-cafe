package com.kakao.cafe.dto.user;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateUserDto {
    private String userId;
    private String password;
    private String name;
    private String email;

    @Builder
    public CreateUserDto(String userId, String password, String name, String email) {
        this.userId = userId;
        this.password = password;
        this.name = name;
        this.email = email;
    }
}
