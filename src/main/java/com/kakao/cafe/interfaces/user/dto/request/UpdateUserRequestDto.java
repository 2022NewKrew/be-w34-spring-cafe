package com.kakao.cafe.interfaces.user.dto.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

public class UpdateUserRequestDto {
    @NotEmpty(message = "비밀번호 입력 값이 비어있습니다")
    private final String prePassword;

    @NotEmpty(message = "비밀번호 입력 값이 비어있습니다")
    private final String changePassword;

    @NotEmpty(message = "이름 입력 값이 비어있습니다")
    private final String name;

    @NotEmpty(message = "이메일 입력 값이 비어있습니다") @Email(message = "이메일 양식이 일치하지 않습니다")
    private final String email;

    public UpdateUserRequestDto(String prePassword, String changePassword, String name, String email) {
        this.prePassword = prePassword;
        this.changePassword = changePassword;
        this.name = name;
        this.email = email;
    }

    public String getPrePassword() {
        return prePassword;
    }

    public String getChangePassword() {
        return changePassword;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

}
