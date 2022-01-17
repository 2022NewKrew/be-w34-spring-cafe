package com.kakao.cafe.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
public class LoginMemberDto {

    @NotEmpty
    private String userId;
    @NotEmpty
    private String password;
}
