package com.kakao.cafe.service;

import com.kakao.cafe.domain.User;
import com.kakao.cafe.domain.UserDto;
import com.kakao.cafe.domain.UserRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final List<User> users = new ArrayList<>();
    private long autoIncrementId = 0;

    public void signUp(UserRequest userRequest) {
        User user = createUser(userRequest);
        users.add(user);
    }

    private User createUser(UserRequest userRequest) {
        return new User(++autoIncrementId, userRequest);
    }

    public List<UserDto> getUsers() {
        return users.stream()
                .map(User::toDto)
                .collect(Collectors.toList());
    }
}
