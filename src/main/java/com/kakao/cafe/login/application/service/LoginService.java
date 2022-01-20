package com.kakao.cafe.login.application.service;

import com.kakao.cafe.login.application.port.in.LoginForm;
import com.kakao.cafe.login.application.port.in.LoginUseCase;
import com.kakao.cafe.login.application.port.in.UserLoginResult;
import com.kakao.cafe.login.application.port.out.LoadLoginInfoPort;
import com.kakao.cafe.login.domain.LoginUser;
import lombok.RequiredArgsConstructor;

import javax.security.auth.login.LoginException;

@RequiredArgsConstructor
public class LoginService implements LoginUseCase {
    private final LoadLoginInfoPort loadLoginInfoPort;

    @Override
    public UserLoginResult login(LoginForm loginForm) throws LoginException {
        LoginUser loginUser = loadLoginInfoPort.findLoginInfo(loginForm.getNickname()).orElseThrow(LoginException::new);
        loginUser.validatePassword(loginForm.getPassword());
        return new UserLoginResult(
                loginUser.getId(),
                loginUser.getNickname()
        );
    }
}
