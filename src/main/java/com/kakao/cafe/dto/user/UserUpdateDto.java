package com.kakao.cafe.dto.user;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserUpdateDto {
    private String prevPassword;
    private String password;
    private String name;
    private String email;
}
