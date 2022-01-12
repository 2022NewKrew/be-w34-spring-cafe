package com.kakao.cafe.repository;

import com.kakao.cafe.model.User;
import com.kakao.cafe.model.Users;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public class MemoryUserRepository implements UserRepository {

    private final Users users;

    public MemoryUserRepository() {
        this.users = new Users(Collections.synchronizedList(new ArrayList<>()));
    }

    @Override
    public User save(User user) {
        users.add(user);
        return user;
    }

    @Override
    public List<User> findAll() {
        return users.getUsers();
    }

    @Override
    public Optional<User> findByUserId(String userId) {
        return users.findByUserId(userId);
    }

    @Override
    public Optional<User> findByName(String name) {
        return users.findByName(name);
    }
}
