package com.kakao.cafe.user.web.dto;

import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserSaveDto {

    @NotBlank(message = "UserId is mandatory")
    private String userId;

    @NotBlank(message = "Password is mandatory")
    private String password;

    @NotBlank(message = "Name is mandatory")
    private String name;

    @NotBlank(message = "Email is mandatory")
    private String email;
}
