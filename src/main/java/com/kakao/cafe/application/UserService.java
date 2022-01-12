package com.kakao.cafe.application;

import com.kakao.cafe.domain.user.User;
import com.kakao.cafe.domain.user.UserRepository;

import java.util.List;
import java.util.Optional;

public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User findByUserId(String userId) throws IllegalArgumentException {
        Optional<User> optionalUser = userRepository.findByUserId(userId);
        if (optionalUser.isEmpty()) {
            throw new IllegalArgumentException();
        }

        return optionalUser.get();
    }

    public List<User> findAllUser() {
        return userRepository.findAll();
    }

    public void join(User user) throws IllegalArgumentException {
        Optional<User> optionalUser = userRepository.findByUserId(user.getUserId());
        if (optionalUser.isPresent()) {
            throw new IllegalArgumentException();
        }

        userRepository.save(user);
    }

}
