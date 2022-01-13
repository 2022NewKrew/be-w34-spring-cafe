package com.kakao.cafe.dto.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserReqDto {
    private String userId;
    private String password;
    private String name;
    private String email;
}
