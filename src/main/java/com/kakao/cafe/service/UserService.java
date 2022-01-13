package com.kakao.cafe.service;

import com.kakao.cafe.dto.UserRegisterRequest;
import com.kakao.cafe.model.User;
import com.kakao.cafe.repository.UserRepository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository jdbcUserRepository) {
        this.userRepository = jdbcUserRepository;
    }

    public List<User> getUserList() {
        return userRepository.findAll();
    }

    public void register(UserRegisterRequest requestDto) {
        userRepository.save(requestDto.toEntity());
    }

    public Optional<User> findById(UUID id) {
        return userRepository.findById(id);
    }

    public Optional<User> findByUserId(String userId) {
        return userRepository.findByUserId(userId);
    }
}
