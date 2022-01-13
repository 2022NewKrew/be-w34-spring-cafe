package com.kakao.cafe.domain;

import lombok.Getter;

/**
 * author    : brody.moon
 * version   : 1.0
 * User 계정 정보 클래스입니다.
 */

@Getter
public class UserAccount {
    private final int id;
    private final String userId;
    private final String password;
    private final String name;
    private final String email;

    public UserAccount(UserAccountDTO userAccountDTO) {
        this.userId = userAccountDTO.getUserId();
        this.password = userAccountDTO.getPassword();
        this.name = userAccountDTO.getName();
        this.email = userAccountDTO.getEmail();
        this.id = userAccountDTO.getId();
    }
}
