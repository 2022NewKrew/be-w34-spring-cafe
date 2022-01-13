package com.kakao.cafe.domain;

import lombok.Data;

/**
 * author    : brody.moon
 * version   : 1.0
 * UserAccount 클래스에서 로직을 제거하고 데이터 전송을 위해 따로 만든 클래스입니다.
 */

@Data
public class UserAccountDTO {
    private int id;
    private String userId;
    private String password;
    private String name;
    private String email;

    public UserAccountDTO(String userId, String password, String name, String email) {
        this.userId = userId;
        this.password = password;
        this.name = name;
        this.email = email;
    }
}
