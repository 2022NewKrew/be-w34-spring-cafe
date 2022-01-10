package com.kakao.cafe.user.web.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserSaveDto {

    private String userId;
    private String password;
    private String name;
    private String email;
}
