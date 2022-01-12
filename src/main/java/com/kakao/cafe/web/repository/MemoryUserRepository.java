package com.kakao.cafe.web.repository;

import com.kakao.cafe.web.domain.User;
import com.kakao.cafe.web.dto.UserDTO;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class MemoryUserRepository implements UserRepository {
    Map<String, User> userMap;

    public MemoryUserRepository() { this.userMap = new HashMap<>(); }

    @Override
    public User save(UserDTO userDTO) {
        User user = new User(userDTO.getUserId(),
                userDTO.getPassword(),
                userDTO.getName(),
                userDTO.getEmail());
        userMap.put(userDTO.getUserId(), user);
        return user;
    }

    @Override
    public List<User> getUserList() {
        return new ArrayList<>(userMap.values());
    }

    @Override
    public User getUserById(String userId) {
        Optional<User> foundUser = userMap.values().stream()
                .filter(user -> user.getUserId().equals(userId))
                .findFirst();
        return foundUser.orElse(null);
    }
}
