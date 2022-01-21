package com.kakao.cafe.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@RequiredArgsConstructor
@Getter
public class UserDTO {
    private final Long id;

    @Size(min = 1, max = 20)
    private final String userId;

    @NotNull
    @Size(min = 1, max = 20)
    private final String password;

    @Email
    @NotNull
    @Size(min = 1, max = 50)
    private final String email;

    private final String time;

}
