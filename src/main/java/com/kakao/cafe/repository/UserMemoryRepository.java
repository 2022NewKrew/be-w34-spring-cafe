package com.kakao.cafe.repository;

import com.kakao.cafe.model.User;
import com.kakao.cafe.model.Users;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class UserMemoryRepository implements UserRepository {

    private final Users users = new Users();

    @Override
    public void signUp(User user) {
        users.addUser(user);
    }

    @Override
    public List<User> findAllUsers() {
        return users.getAllUsers();
    }

    @Override
    public User findUserByUserId(String userId) {
        return users.findUserById(userId);
    }
}
