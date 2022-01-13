package com.kakao.cafe.dto.user;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@AllArgsConstructor
public class UserDTO {

    @NotBlank
    private Long id;

    @NotBlank
    private String userId;

    @NotBlank
    private String name;

    @NotBlank
    @Email
    private String email;
}
