package com.kakao.cafe.user.application;

import com.kakao.cafe.user.application.port.in.SignUpUseCase;
import com.kakao.cafe.user.application.port.in.SignUpUserDto;
import com.kakao.cafe.user.application.port.out.SaveUserPort;
import com.kakao.cafe.user.domain.UserId;

public class SignUpService implements SignUpUseCase {

    private final SaveUserPort saveUserPort;

    public SignUpService(SaveUserPort saveUserPort) {
        this.saveUserPort = saveUserPort;
    }

    @Override
    public UserId signUp(SignUpUserDto signUpUserDto) {
        return saveUserPort.save(signUpUserDto.toCreateUserDto());
    }
}
