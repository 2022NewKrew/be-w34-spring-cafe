package com.kakao.cafe.login.application.service;

import com.kakao.cafe.login.application.port.in.LoginForm;
import com.kakao.cafe.login.application.port.in.LoginUseCase;
import com.kakao.cafe.login.application.port.in.UserLoginCommand;
import com.kakao.cafe.login.application.port.out.LoadLoginInfoPort;
import com.kakao.cafe.login.domain.LoginUser;
import lombok.RequiredArgsConstructor;

import javax.security.auth.login.LoginException;
import java.util.Optional;

@RequiredArgsConstructor
public class LoginService implements LoginUseCase {
    private final LoadLoginInfoPort loadLoginInfoPort;

    @Override
    public UserLoginCommand login(LoginForm loginForm) throws LoginException {
        Optional<LoginUser> optionalLoginUser = loadLoginInfoPort.findLoginInfo(loginForm.getNickname());
        LoginUser loginUser = optionalLoginUser.orElseThrow(LoginException::new);
        loginUser.validatePassword(loginForm.getPassword());
        return new UserLoginCommand(
                loginUser.getId(),
                loginUser.getNickname()
        );
    }
}
