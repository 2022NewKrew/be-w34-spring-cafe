package com.kakao.cafe.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void registerUser(User user) {
        if (userRepository.get(user.getUsername()).isPresent()) {
            throw new RuntimeException("Username is already in use: " + user.getUsername());
        }

        userRepository.add(user);
    }

    public List<User> getAllUsers() {
        return userRepository.getAll();
    }

    public User findUserByUsername(String username) {
        return userRepository.get(username).orElseThrow(
                () -> new NoSuchElementException("Username not found: " + username));
    }
}
