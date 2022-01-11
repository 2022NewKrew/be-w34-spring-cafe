package com.kakao.cafe.service;

import com.kakao.cafe.domain.User;
import com.kakao.cafe.dto.UserCreationDTO;
import com.kakao.cafe.repository.MemoryUserRepository;
import com.kakao.cafe.repository.UserRepository;
import com.kakao.cafe.util.Encrypt;

import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;

public class UserService {

    private UserRepository userRepository;
    private Encrypt encrypt;

    public UserService() {
        userRepository = new MemoryUserRepository();
        try {
            encrypt = new Encrypt();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    public long join(UserCreationDTO dto) {
        User newUser = new User(encrypt, dto);

        validateDuplicateUserId(newUser);
        validateDuplicateUserEmail(newUser);
        userRepository.save(newUser);

        return newUser.getId();
    }

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> findById(long id) {
        return userRepository.findById(id);
    }

    public Optional<User> findByUserId(String userId) {
        return userRepository.findByUserId(userId);
    }

    private void validateDuplicateUserId(User newUser) {
        userRepository.findByUserId(newUser.getUserId()).ifPresent(m -> {
            throw new IllegalStateException("이미 존재하는 아이디입니다.");
        });
    }

    private void validateDuplicateUserEmail(User newUser) {
        userRepository.findByUserId(newUser.getUserEmail()).ifPresent(m -> {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        });
    }
}
