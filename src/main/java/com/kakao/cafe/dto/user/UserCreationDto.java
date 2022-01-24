package com.kakao.cafe.dto.user;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class UserCreationDto {
    @NotEmpty(message = "이메일은 필수 입력 값입니다")
    private String email;
    @NotEmpty(message = "닉네임은 필수 입력 값입니다.")
    private String nickname;
    @NotEmpty(message = "패스워드는 필수 입력 값입니다.")
    private String password;
    private String prevPassword;
}
