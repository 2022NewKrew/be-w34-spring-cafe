package com.kakao.cafe.service;

import com.kakao.cafe.domain.User;
import com.kakao.cafe.domain.UserDto;
import com.kakao.cafe.domain.UserRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final List<User> users = new CopyOnWriteArrayList<>();
    private final AtomicLong sequenceId = new AtomicLong();

    public void signUp(UserRequest userRequest) {
        User user = createUser(userRequest);
        users.add(user);
    }

    private User createUser(UserRequest userRequest) {
        return new User(sequenceId.incrementAndGet(), userRequest);
    }

    public List<UserDto> getUsers() {
        return users.stream()
                .map(User::toDto)
                .collect(Collectors.toList());
    }

    public UserDto findById(Long id) {
        return users.stream()
                .filter(u -> u.isEqualUserId(id))
                .findFirst()
                .map(User::toDto)
                .orElse(null);
    }
}
