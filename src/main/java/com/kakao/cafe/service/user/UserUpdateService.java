package com.kakao.cafe.service.user;

import com.kakao.cafe.domain.user.Password;
import com.kakao.cafe.domain.user.User;
import com.kakao.cafe.repository.user.UserRepository;
import org.springframework.transaction.annotation.Transactional;

public class UserUpdateService {

    private final UserRepository userRepository;

    public UserUpdateService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public void update(User user, Password password) {
        correctPasswordCheck(userRepository.findById(user.getUserId()), password);
        userRepository.update(user.getUserId(), password, user);
    }

    private void correctPasswordCheck(User user, Password password) {
        if(!user.getPassword().equals(password)) {
            throw new IllegalArgumentException("Password Incorrect : " + user.getUserId());
        }
    }
}
