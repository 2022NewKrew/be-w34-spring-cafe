package com.kakao.cafe.user.application;

import com.kakao.cafe.exception.UserNotFoundException;
import com.kakao.cafe.user.application.port.in.FoundUserDto;
import com.kakao.cafe.user.application.port.in.LoginUseCase;
import com.kakao.cafe.user.application.port.out.LoadUserPort;
import com.kakao.cafe.user.domain.Email;
import com.kakao.cafe.user.domain.Password;
import com.kakao.cafe.user.domain.User;
import java.util.Optional;

public class LoginService implements LoginUseCase {

    private final LoadUserPort loadUserPort;

    public LoginService(LoadUserPort loadUserPort) {
        this.loadUserPort = loadUserPort;
    }

    @Override
    public FoundUserDto login(Email email, Password password) {
        Optional<User> optionalUser = loadUserPort.loadByEmail(email);
        return optionalUser.map(
                user -> {
                    user.isValidPassword(password);
                    return new FoundUserDto(user.getUserId(), user.getEmail(), user.getNickname());
                })
            .orElseThrow(UserNotFoundException::new);
    }
}
