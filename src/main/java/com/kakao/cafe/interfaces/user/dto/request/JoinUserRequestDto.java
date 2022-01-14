package com.kakao.cafe.interfaces.user.dto.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

public class JoinUserRequestDto {
    @NotEmpty(message = "ID 입력 값이 비어있습니다")
    private final String userId;

    @NotEmpty(message = "비밀번호 입력 값이 비어있습니다")
    private final String password;

    @NotEmpty(message = "이름 입력 값이 비어있습니다")
    private final String name;

    @NotEmpty(message = "이메일 입력 값이 비어있습니다") @Email(message = "이메일 양식이 일치하지 않습니다")
    private final String email;

    public JoinUserRequestDto(String userId, String password, String name, String email) {
        this.userId = userId;
        this.password = password;
        this.name = name;
        this.email = email;
    }

    public String getUserId() {
        return userId;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}
