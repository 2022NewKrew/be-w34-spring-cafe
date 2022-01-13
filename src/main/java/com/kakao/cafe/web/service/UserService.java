package com.kakao.cafe.web.service;

import com.kakao.cafe.web.domain.User;
import com.kakao.cafe.web.repository.user.UserRepository;

import java.util.List;
import java.util.Optional;

public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User join(User user) {
        validateDuplicateUser(user);
        userRepository.save(user);
        return user;
    }

    private void validateDuplicateUser(User user) {
        userRepository.findByUserId(user.getUserId()).
                ifPresent(u -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    public List<User> findUsers() {
        return userRepository.findAll();
    }

    public Optional<User> findUser(String userId) {
        return userRepository.findByUserId(userId);
    }
}
