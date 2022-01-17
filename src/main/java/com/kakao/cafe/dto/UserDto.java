package com.kakao.cafe.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDto {
    private Long id;
    private String email;
    private String nickName;
    private String password;
    private String registeredDate;
}
