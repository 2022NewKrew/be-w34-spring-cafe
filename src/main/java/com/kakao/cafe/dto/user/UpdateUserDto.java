package com.kakao.cafe.dto.user;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class UpdateUserDto {
    private String password;
    private String newPassword;
    @NotBlank(message = "이름을 입력하세요.")
    private String name;
    @NotBlank(message = "이메일을 입력하세요")
    private String email;

    @Builder
    public UpdateUserDto(String password, String newPassword, String name, String email) {
        this.password = password;
        this.newPassword = newPassword;
        this.name = name;
        this.email = email;
    }
}
