package com.kakao.cafe.dao;

import com.kakao.cafe.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

@Repository
public interface UserDAO {

    List<User> users = new ArrayList<>();

    default List<User> findAllUser() {
        return users;
    }

    default User filterUserById(String userId) {
        return users.stream()
                .filter(user -> user.getUserId().equals(userId))
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    default void createUser(User user) {
        users.add(user);
    }

}
