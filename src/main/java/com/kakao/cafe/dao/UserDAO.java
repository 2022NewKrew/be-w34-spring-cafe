package com.kakao.cafe.dao;

import com.kakao.cafe.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

@Repository
public class UserDAO {

    private final List<User> users = new ArrayList<>();

    public List<User> findAllUser() {
        return users;
    }

    public User filterUserById(String userId) {
        return users.stream()
                .filter(user -> user.getUserId().equals(userId))
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public void createUser(User user) {
        users.add(user);
    }

}
