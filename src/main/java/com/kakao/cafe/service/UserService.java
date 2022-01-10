package com.kakao.cafe.service;

import com.kakao.cafe.entity.User;
import com.kakao.cafe.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User create(String id, String password, String name, String email) {
        User user = new User.Builder()
                .id(id)
                .password(password)
                .name(name)
                .email(email)
                .build();
        userRepository.create(user);
        return user;
    }

    public List<User> list() {
        return userRepository.list();
    }

    public User get(String id) {
        return userRepository.get(id);
    }
}
