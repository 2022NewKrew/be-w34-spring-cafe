package com.kakao.cafe.application.user.port.in;

import com.kakao.cafe.application.user.dto.LoginRequest;
import com.kakao.cafe.domain.user.exceptions.UserNotExistException;
import com.kakao.cafe.domain.user.exceptions.WrongPasswordException;

public interface LoginUserUseCase {

    void login(LoginRequest loginRequest) throws UserNotExistException, WrongPasswordException;
}
