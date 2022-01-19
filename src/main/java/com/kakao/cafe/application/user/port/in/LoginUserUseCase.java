package com.kakao.cafe.application.user.port.in;

import com.kakao.cafe.application.user.dto.LoginRequest;
import com.kakao.cafe.application.user.dto.UserInfo;
import com.kakao.cafe.domain.user.exceptions.UserNotExistException;
import com.kakao.cafe.domain.user.exceptions.WrongPasswordException;

public interface LoginUserUseCase {

    UserInfo login(LoginRequest loginRequest) throws UserNotExistException, WrongPasswordException;
}
