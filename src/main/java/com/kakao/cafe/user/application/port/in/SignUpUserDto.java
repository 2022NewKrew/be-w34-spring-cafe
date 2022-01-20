package com.kakao.cafe.user.application.port.in;

import com.kakao.cafe.user.application.port.out.CreateUserDto;
import com.kakao.cafe.user.domain.Email;
import com.kakao.cafe.user.domain.Password;

public class SignUpUserDto {

    private final Email email;
    private final String nickname;
    private final Password password;

    public SignUpUserDto(Email email, String nickname, Password password) {
        this.email = email;
        this.nickname = nickname;
        this.password = password;
    }

    public CreateUserDto toCreateUserDto() {
        return new CreateUserDto(email.getValue(), nickname, password.getValue());
    }

    public Email getEmail() {
        return email;
    }

    public String getNickname() {
        return nickname;
    }

    public Password getPassword() {
        return password;
    }
}
