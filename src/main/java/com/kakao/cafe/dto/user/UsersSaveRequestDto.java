package com.kakao.cafe.dto.user;

import lombok.Getter;

@Getter
public class UsersSaveRequestDto {
    private final String accId; // accountId
    private final String accPw; // accountPassword
    private final String name;
    private final String email;

    public UsersSaveRequestDto(String accId, String accPw, String name, String email){
        this.accId = accId;
        this.accPw = accPw;
        this.name = name;
        this.email = email;
    }
}