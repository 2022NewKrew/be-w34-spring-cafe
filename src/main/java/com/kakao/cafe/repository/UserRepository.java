package com.kakao.cafe.repository;


import com.kakao.cafe.vo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository("userRepository")
public class UserRepository {
    private final List<User> users = new ArrayList<>();

//    @Autowired
//    private PasswordEncoder passwordEncoder;

    public void createUser(User user) {
//        user.setPassword(passwordEncoder.encode(user.getPassword()));
        users.add(user);
    }

    public List<User> readUsers() {
        return users;
    }

    public User readUser(String userId) {
        return users.stream()
                .filter(user -> Objects.equals(user.getUserId(), userId))
                .findFirst()
                .orElse(null);
    }

}
