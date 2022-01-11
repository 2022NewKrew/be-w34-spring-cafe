package com.kakao.cafe.web.repository;

import com.kakao.cafe.web.domain.User;
import com.kakao.cafe.web.dto.UserDTO;

import java.util.*;

public class MemoryUserRepository implements UserRepository {
    Map<String, User> userList;

    public MemoryUserRepository() { this.userList = new HashMap<>(); }

    @Override
    public User save(UserDTO userDTO) {
        User user = new User(userDTO.getUserId(),
                userDTO.getPassword(),
                userDTO.getName(),
                userDTO.getEmail());
        userList.put(userDTO.getUserId(), user);
        return user;
    }

    @Override
    public List<User> getUserList() {
        return new ArrayList<User>(userList.values());
    }

    @Override
    public User getUserById(String userId) {
        Optional<User> foundUser = userList.values().stream()
                .filter(user -> user.getUserId().equals(userId))
                .findFirst();
        return foundUser.orElse(null);
    }
}
