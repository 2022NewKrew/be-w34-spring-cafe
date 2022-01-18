package com.kakao.cafe.user.application.service;

import com.kakao.cafe.common.exception.SignUpException;
import com.kakao.cafe.user.application.port.in.UserRegistrationCommand;
import com.kakao.cafe.user.application.port.in.UserSignUpUseCase;
import com.kakao.cafe.user.application.port.out.UserCreateCommand;
import com.kakao.cafe.user.application.port.out.UserRegistrationPort;

import java.time.LocalDateTime;

public class UserSignUpService implements UserSignUpUseCase {
    private final UserRegistrationPort userRegistrationPort;

    public UserSignUpService(UserRegistrationPort userRegistrationPort) {
        this.userRegistrationPort = userRegistrationPort;
    }

    @Override
    public void saveUser(UserRegistrationCommand userRegistrationCommand) {
        checkAlreadyExistNickname(userRegistrationCommand);
        userRegistrationPort.saveUser(new UserCreateCommand(
                        LocalDateTime.now(),
                        userRegistrationCommand.getNickname(),
                        userRegistrationCommand.getEmail(),
                        userRegistrationCommand.getName(),
                        userRegistrationCommand.getPassword()
                )
        );
    }

    private void checkAlreadyExistNickname(UserRegistrationCommand userRegistrationCommand) {
        Boolean isAlreadyExistNickname = userRegistrationPort.isAlreadyExistNickname(userRegistrationCommand.getNickname());
        if (isAlreadyExistNickname) throw new SignUpException();
    }
}
