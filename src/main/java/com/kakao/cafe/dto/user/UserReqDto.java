package com.kakao.cafe.dto.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class UserReqDto {
    private String userId;
    private String password;
    private String name;
    private String email;
}
