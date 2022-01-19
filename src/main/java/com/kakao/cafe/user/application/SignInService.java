package com.kakao.cafe.user.application;

import com.kakao.cafe.exception.UserNotFoundException;
import com.kakao.cafe.user.application.port.in.FoundUserDto;
import com.kakao.cafe.user.application.port.in.SignInUseCase;
import com.kakao.cafe.user.application.port.out.LoadUserPort;
import com.kakao.cafe.user.domain.Password;
import com.kakao.cafe.user.domain.User;
import com.kakao.cafe.user.domain.UserId;
import java.util.Optional;

public class SignInService implements SignInUseCase {

    private final LoadUserPort loadUserPort;

    public SignInService(LoadUserPort loadUserPort) {
        this.loadUserPort = loadUserPort;
    }

    @Override
    public FoundUserDto signIn(UserId userId, Password password) {
        Optional<User> optionalUser = loadUserPort.load(userId);
        return optionalUser.map(
                user -> {
                    user.isValidPassword(password);
                    return new FoundUserDto(user.getUserId(), user.getEmail(), user.getNickname());
                })
            .orElseThrow(UserNotFoundException::new);
    }
}
