package com.kakao.cafe.domain.login;

import com.kakao.cafe.infra.repository.user.UserRepository;

/**
 * To-do
 */
public class LoginService {
    private final UserRepository userRepository;

    public LoginService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

}
