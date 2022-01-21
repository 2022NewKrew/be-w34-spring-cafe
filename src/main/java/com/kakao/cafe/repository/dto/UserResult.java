package com.kakao.cafe.repository.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserResult {
    private String userId;
    private String name;
    private String email;
    private String password;
}
