package com.kakao.cafe.app.request;

import com.kakao.cafe.service.dto.SignUpDto;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;

public class SignUpRequest {

    @Length(min=4, max=20)
    private final String userId;

    @Length(min=6, max=20)
    private final String password;

    @Length(min=2, max=20)
    private final String name;

    @Email
    private final String email;

    public SignUpRequest(
            String userId,
            String password,
            String name,
            String email
    ) {
        this.userId = userId;
        this.password = password;
        this.name = name;
        this.email = email;
    }

    public SignUpDto toSignUpDto() {
        return new SignUpDto(userId, password, name, email);
    }
}
