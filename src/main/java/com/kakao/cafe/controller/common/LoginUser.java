package com.kakao.cafe.controller.common;

import com.kakao.cafe.user.UserStatus;
import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class LoginUser {
    private Long id;
    private String userId;
    private String name;
    private String email;
    private UserStatus role;
}
