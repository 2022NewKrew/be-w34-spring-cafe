package com.kakao.cafe.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserUpdateRequest {

    private String userId;
    private String password;
    private String newPassword;
    private String name;
    private String email;

}
