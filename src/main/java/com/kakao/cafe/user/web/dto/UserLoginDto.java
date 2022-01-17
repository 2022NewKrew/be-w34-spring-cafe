package com.kakao.cafe.user.web.dto;

import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class UserLoginDto {

    @NotBlank(message = "UserId is mandatory")
    private String userId;

    @NotBlank(message = "Password is mandatory")
    private String password;
}
