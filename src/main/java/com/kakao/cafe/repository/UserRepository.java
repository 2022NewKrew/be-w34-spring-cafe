package com.kakao.cafe.repository;

import com.kakao.cafe.dto.UserRegistrationDto;
import com.kakao.cafe.entity.User;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class UserRepository {
    private final List<User> users = new ArrayList<>();
    private final PasswordEncoder passwordEncoder;

    public UserRepository(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public void createUser(UserRegistrationDto userDto) {
        User user = new User(userDto.getUserId(), passwordEncoder.encode(userDto.getPassword()), userDto.getEmail());
        users.add(user);
    }

    public List<User> readUsers() {
        return users;
    }

    public User readUser(String userId) {
        return users.stream()
                .filter(user -> Objects.equals(user.getUserId(), userId))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
