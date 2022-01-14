package com.kakao.cafe.application.user.service;

import com.kakao.cafe.application.user.dto.SignUpRequest;
import com.kakao.cafe.application.user.port.in.SignUpUserUseCase;
import com.kakao.cafe.application.user.port.out.RegisterUserPort;
import com.kakao.cafe.domain.user.exceptions.IllegalEmailException;
import com.kakao.cafe.domain.user.exceptions.IllegalPasswordException;
import com.kakao.cafe.domain.user.exceptions.IllegalUserIdException;
import com.kakao.cafe.domain.user.exceptions.IllegalUserNameException;
import com.kakao.cafe.domain.user.exceptions.UserIdDuplicationException;

public class SignUpUserService implements SignUpUserUseCase {

    private final RegisterUserPort registerUserPort;

    public SignUpUserService(RegisterUserPort registerUserPort) {
        this.registerUserPort = registerUserPort;
    }

    @Override
    public void signUpUser(SignUpRequest signUpRequest)
        throws IllegalUserIdException, IllegalPasswordException, IllegalUserNameException, IllegalEmailException, UserIdDuplicationException {
        registerUserPort.registerUser(signUpRequest);
    }
}
