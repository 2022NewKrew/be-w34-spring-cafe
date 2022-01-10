package com.kakao.cafe.service;

import com.kakao.cafe.domain.User;
import com.kakao.cafe.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    public String join(User user) {
        userRepository.save(user);
        return user.getUserId();
    }

    public List<User> findEvery() {
        return userRepository.findAll();
    }

    public User findUser(String userId) {
        return userRepository.findById(userId);
    }
}
