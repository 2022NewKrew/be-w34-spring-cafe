package com.kakao.cafe.app.request;

import com.kakao.cafe.service.dto.CredentialsDto;
import org.hibernate.validator.constraints.Length;

public class LoginRequest {

    @Length(min=6, max=20)
    private final String userId;

    @Length(min=6, max=20)
    private final String password;

    public LoginRequest(String userId, String password) {
        this.userId = userId;
        this.password = password;
    }

    public CredentialsDto toCredentialsDto() {
        return new CredentialsDto(userId, password);
    }
}
