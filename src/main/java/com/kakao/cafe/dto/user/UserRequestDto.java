package com.kakao.cafe.dto.user;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserRequestDto {
    private String stringId;
    private String prevPassword;
    private String password;
    private String name;
    private String email;
}
