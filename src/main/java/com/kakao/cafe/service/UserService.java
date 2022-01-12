package com.kakao.cafe.service;

import com.kakao.cafe.dto.UserRegisterRequest;
import com.kakao.cafe.model.User;
import com.kakao.cafe.repository.UserRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getUserList() {
        return userRepository.findAll();
    }

    public User register(UserRegisterRequest requestDto) {
        return userRepository.save(requestDto.toEntity());
    }

    public Optional<User> findByUserId(String userId) {
        return userRepository.findByUserId(userId);
    }
}
