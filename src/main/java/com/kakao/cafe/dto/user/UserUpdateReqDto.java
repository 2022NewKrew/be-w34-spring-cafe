package com.kakao.cafe.dto.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class UserUpdateReqDto {
    private Long id;
    private String password;
    private String newPassword;
    private String name;
    private String email;
}
