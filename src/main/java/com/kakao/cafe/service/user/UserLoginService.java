package com.kakao.cafe.service.user;

import com.kakao.cafe.domain.user.Email;
import com.kakao.cafe.domain.user.Password;
import com.kakao.cafe.domain.user.User;
import com.kakao.cafe.repository.user.UserRepository;
import org.springframework.transaction.annotation.Transactional;

public class UserLoginService {
    private static final String INCORRECT_EMAIL_OR_PASSWORD = "비밀번호가 맞지 않거나 존재하지 않는 이메일입니다.";

    private final UserRepository userRepository;

    public UserLoginService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional(readOnly = true)
    public User login(Email email, Password password) {
        return findLoginUser(email, password);
    }

    private User findLoginUser(Email email, Password password) {
        User user = userRepository.findByEmail(email);
        try {
            validateUser(user, password);
        } catch (NullPointerException e) {
            throw new IllegalArgumentException(INCORRECT_EMAIL_OR_PASSWORD);
        }
        return user;
    }

    private void validateUser(User user, Password password) throws NullPointerException, IllegalArgumentException {
        if(!user.getPassword().equals(password)) {
            throw new IllegalArgumentException(INCORRECT_EMAIL_OR_PASSWORD);
        }
    }
}
