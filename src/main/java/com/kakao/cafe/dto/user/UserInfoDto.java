package com.kakao.cafe.dto.user;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserInfoDto {
    private String userId;
    private String name;
    private String email;
}
