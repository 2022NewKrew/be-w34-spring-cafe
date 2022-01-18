package com.kakao.cafe.application.user.service;

import com.kakao.cafe.application.user.dto.LoginRequest;
import com.kakao.cafe.application.user.port.in.LoginUserUseCase;
import com.kakao.cafe.application.user.port.out.LoginUserPort;
import com.kakao.cafe.domain.user.User;
import com.kakao.cafe.domain.user.exceptions.UserNotExistException;
import com.kakao.cafe.domain.user.exceptions.WrongPasswordException;

public class LoginUserService implements LoginUserUseCase {

    private final LoginUserPort loginUserPort;

    public LoginUserService(LoginUserPort loginUserPort) {
        this.loginUserPort = loginUserPort;
    }

    @Override
    public void login(LoginRequest loginRequest) throws UserNotExistException, WrongPasswordException {
        User user = loginUserPort.login(loginRequest);
        loginRequest.checkPassword(user);
    }
}
