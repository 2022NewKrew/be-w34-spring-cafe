package com.kakao.cafe.user.service;

import com.kakao.cafe.user.entity.User;
import com.kakao.cafe.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void addUser(User user) {
        if (validateUser(user.getUserId())) {
            userRepository.save(user);
            return;
        }
        throw new IllegalStateException("이미 가입되어 있는 userID입니다.");
    }

    public User loginUser(String userId, String password) {
        return userRepository.findOneByUserId(userId)
                .filter(user -> user.getPassword().equals(password))
                .orElse(null);
    }

    private boolean validateUser(String userId) {
        return userRepository.findOneByUserId(userId).isEmpty();
    }

    public List<User> findUsers() {
        return userRepository.findAll();
    }

    public Optional<User> findOneByUserId(String userId) {
        return userRepository.findOneByUserId(userId);
    }

    public void update(User user) {
        userRepository.update(user);
    }

}
