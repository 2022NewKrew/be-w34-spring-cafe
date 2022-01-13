package com.kakao.cafe.dto.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserUpdateReqDto {
    private Long id;
    private String password;
    private String newPassword;
    private String name;
    private String email;
}
