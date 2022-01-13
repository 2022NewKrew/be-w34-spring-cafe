package com.kakao.cafe.user.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class UserLoginDto {
    @NotBlank(message = "userId를 입력해주세요.")
    String userId;
    @NotBlank(message = "비밀번호를 입력해주세요.")
    String password;
}
