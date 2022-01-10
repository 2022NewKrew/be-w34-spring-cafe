package com.kakao.cafe.service;

import com.kakao.cafe.dto.UserDto;
import com.kakao.cafe.entity.User;
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
    public UserDto create(String id, String password, String name, String email) {
        User user = new User.Builder()
                .id(id)
                .password(password)
                .name(name)
                .email(email)
                .build();
        User created = userRepository.create(user);
        if (created == null) {
            return null;
        }
        return created.toDto();
    }

    public List<UserDto> list() {
        return userRepository.list()
                .stream()
                .map(User::toDto)
                .collect(Collectors.toList());
    }

    @Nullable
    public UserDto get(String id) {
        User found = userRepository.get(id);
        if (found == null) {
            return null;
        }
        return found.toDto();
    }

    @Nullable
    public UserDto login(String id, String password) {
        User found = userRepository.login(id, password);
        if (found == null) {
            return null;
        }
        return found.toDto();
    }
}
