package com.kakao.cafe.domain.user;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class UserSaveDto {
    @NotBlank(message = "이메일은 공백일 수 없습니다.")
    @Email(message = "이메일 형식이 아닙니다.")
    private final String email;
    @NotBlank(message = "이름은 공백일 수 없습니다.")
    private final String name;
    @NotBlank(message = "비밀번호는 공백일 수 없습니다.")
    private final String password;

    public UserSaveDto(String email, String name, String password) {
        this.email = email;
        this.name = name;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }
}
