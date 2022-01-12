package com.kakao.cafe.dto.user;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateUserDto {
    private String password;
    private String newPassword;
    private String name;
    private String email;

    @Builder
    public UpdateUserDto(String password, String newPassword, String name, String email) {
        this.password = password;
        this.newPassword = newPassword;
        this.name = name;
        this.email = email;
    }
}
