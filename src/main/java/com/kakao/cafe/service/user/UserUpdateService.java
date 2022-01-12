package com.kakao.cafe.service.user;

import com.kakao.cafe.domain.user.User;
import com.kakao.cafe.domain.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserUpdateService {

    private final UserRepository userRepository;

    @Autowired
    public UserUpdateService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public void update(String userId, String password, User user) {
        validCorrectPassword(userRepository.findById(userId), password);
        userRepository.update(userId, password, user);
    }

    private void validCorrectPassword(User user, String password) {
        if(!user.getPassword().equals(password)) {
            throw new IllegalArgumentException("Password Incorrect : " + user.getUserId());
        }
    }
}
