package com.kakao.cafe.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserCreationDTO {
    private String userEmail;
    private String userId;
    private String password;
}
