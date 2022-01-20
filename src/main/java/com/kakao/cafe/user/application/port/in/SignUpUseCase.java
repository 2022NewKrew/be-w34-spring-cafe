package com.kakao.cafe.user.application.port.in;

import com.kakao.cafe.user.domain.UserId;

public interface SignUpUseCase {

    UserId signUp(SignUpUserDto signUpUserDto);

}
