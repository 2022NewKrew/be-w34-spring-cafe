package com.kakao.cafe.login.application.port.in;

import javax.security.auth.login.LoginException;

public interface LoginUseCase {
    UserLoginResult login(LoginForm loginForm) throws LoginException;
}
