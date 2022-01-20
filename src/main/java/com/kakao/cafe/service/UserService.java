package com.kakao.cafe.service;

import com.kakao.cafe.domain.user.User;
import com.kakao.cafe.repository.RepositoryInterface;

import java.util.List;

public class UserService {
    private final RepositoryInterface<User> userRepository;

    public UserService(RepositoryInterface<User> userRepository) {
        this.userRepository = userRepository;
    }

    public void join(User user) {
        validateDuplicateUser(user);

        userRepository.save(user);
    }

    private void validateDuplicateUser(User user) {
        // 중복 ID 체크
        userRepository.findByName(user.getEmail())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 ID입니다.");
                });
    }

    public void updateUser(User user) {
        userRepository.update(user);
    }

    public List<User> findUsers() {
        return userRepository.findAll();
    }

    public User findOne(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow();
    }

    public User findEmail(String email) {
        return userRepository.findByName(email)
                .orElseThrow();
    }
}
