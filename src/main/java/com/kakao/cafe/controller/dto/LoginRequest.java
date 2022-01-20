package com.kakao.cafe.controller.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequest {
    String nickname;
    String password;
}
