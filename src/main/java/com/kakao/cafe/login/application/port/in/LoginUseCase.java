package com.kakao.cafe.login.application.port.in;

import javax.security.auth.login.LoginException;

public interface LoginUseCase {
    UserLoginCommand login(LoginForm loginForm) throws LoginException;
}
