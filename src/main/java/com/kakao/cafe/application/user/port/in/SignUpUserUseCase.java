package com.kakao.cafe.application.user.port.in;

import com.kakao.cafe.application.user.dto.SignUpRequest;

public interface SignUpUserUseCase {

    void signUpUser(SignUpRequest signUpRequest);
}
