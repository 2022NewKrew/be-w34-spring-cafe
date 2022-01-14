package com.kakao.cafe.service;

import com.kakao.cafe.model.User;
import com.kakao.cafe.repository.UserMemoryRepository;
import com.kakao.cafe.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public void signUp(User user) {
        userRepository.signUp(user);
    }

    public List<User> findAllUsers() {
        return userRepository.findAllUsers();
    }

    public User findUserById(String userId) {
        return userRepository.findUserByUserId(userId);
    }
}
