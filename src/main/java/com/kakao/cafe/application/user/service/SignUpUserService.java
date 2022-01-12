package com.kakao.cafe.application.user.service;

import com.kakao.cafe.application.user.dto.SignUpRequest;
import com.kakao.cafe.application.user.port.in.SignUpUserUseCase;
import com.kakao.cafe.application.user.port.out.RegisterUserPort;
import com.kakao.cafe.domain.user.User;
import org.springframework.stereotype.Service;

@Service
public class SignUpUserService implements SignUpUserUseCase {

    private final RegisterUserPort registerUserPort;

    public SignUpUserService(RegisterUserPort registerUserPort) {
        this.registerUserPort = registerUserPort;
    }

    @Override
    public void signUpUser(SignUpRequest signUpRequest) {
        registerUserPort.registerUser(new User(
            signUpRequest.getUserId(),
            signUpRequest.getPassword(),
            signUpRequest.getName(),
            signUpRequest.getEmail()
        ));
    }
}
