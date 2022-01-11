package com.kakao.cafe.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class User {
    private long id;
    private String userId;
    private String userName;
    private String userPw;
    private String userEmail;
}
