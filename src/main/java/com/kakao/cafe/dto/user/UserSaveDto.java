package com.kakao.cafe.dto.user;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserSaveDto {
    private String stringId;
    private String password;
    private String name;
    private String email;
}
