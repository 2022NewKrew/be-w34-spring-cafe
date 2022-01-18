package com.kakao.cafe.application.user.port.out;

import com.kakao.cafe.application.user.dto.LoginRequest;
import com.kakao.cafe.domain.user.exceptions.UserNotExistException;
import com.kakao.cafe.domain.user.exceptions.WrongPasswordException;

public interface LoginUserPort {

    void login(LoginRequest loginRequest) throws UserNotExistException, WrongPasswordException;
}
