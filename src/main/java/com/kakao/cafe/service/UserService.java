package com.kakao.cafe.service;

import com.kakao.cafe.dto.User;
import com.kakao.cafe.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Nullable
    public User create(String id, String password, String name, String email) {
        com.kakao.cafe.entity.User user = new com.kakao.cafe.entity.User.Builder()
                .id(id)
                .password(password)
                .name(name)
                .email(email)
                .build();
        com.kakao.cafe.entity.User created = userRepository.create(user);
        if (created == null) {
            return null;
        }
        return created.toDto();
    }

    public List<User> list() {
        return userRepository.list()
                .stream()
                .map(com.kakao.cafe.entity.User::toDto)
                .collect(Collectors.toList());
    }

    @Nullable
    public User get(String id) {
        com.kakao.cafe.entity.User found = userRepository.get(id);
        if (found == null) {
            return null;
        }
        return found.toDto();
    }

    @Nullable
    public User login(String id, String password) {
        com.kakao.cafe.entity.User found = userRepository.login(id, password);
        if (found == null) {
            return null;
        }
        return found.toDto();
    }
}
