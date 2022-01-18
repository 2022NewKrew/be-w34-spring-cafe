package com.kakao.cafe.domain.user;

import com.kakao.cafe.infra.dao.UserCreateCommand;
import com.kakao.cafe.infra.repository.user.UserRepository;
import com.kakao.cafe.web.user.form.UserRegistrationForm;

import java.time.LocalDateTime;

public class UserSignUpService {
    private final UserRepository userRepository;

    public UserSignUpService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void saveUser(UserRegistrationForm userRegistrationForm) {
        checkAlreadyExistNickname(userRegistrationForm);
        userRepository.saveUser(new UserCreateCommand(
                LocalDateTime.now(),
                userRegistrationForm.getNickname(),
                userRegistrationForm.getEmail(),
                userRegistrationForm.getName(),
                userRegistrationForm.getPassword()
                )
        );
    }

    private void checkAlreadyExistNickname(UserRegistrationForm userRegistrationForm) {
        Boolean isAlreadyExistNickname = userRepository.isAlreadyExistNickname(userRegistrationForm.getNickname());
        if (isAlreadyExistNickname) throw new RuntimeException("이미 존재하는 유저아이디 입니다.");
    }
}
