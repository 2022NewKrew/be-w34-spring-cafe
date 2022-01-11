package com.kakao.cafe.domain;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class User {
    private String userId;
    private String userName;
    private String userPw;
    private String userEmail;

    private long key;
}