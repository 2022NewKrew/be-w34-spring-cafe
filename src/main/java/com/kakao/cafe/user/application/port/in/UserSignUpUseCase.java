package com.kakao.cafe.user.application.port.in;

public interface UserSignUpUseCase {
    void saveUser(UserRegistrationCommand userRegistrationCommand);
}
