package com.kakao.cafe.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class UserFormDTO {
    private String userId;
    private String userName;
    private String userPw;
    private String userEmail;
}
