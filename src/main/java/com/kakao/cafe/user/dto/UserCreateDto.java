package com.kakao.cafe.user.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

/**
 *
 * 회원가입폼에 대한 DTO 입니다.
 * @author jm.hong
 */
@Getter
@Setter
public class UserCreateDto {
    @NotEmpty
    private String userId;
    @NotEmpty
    private String password;
    @NotEmpty
    private String name;
    @NotEmpty @Email
    private String email;
}
