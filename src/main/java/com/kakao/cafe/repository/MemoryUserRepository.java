package com.kakao.cafe.repository;

import com.kakao.cafe.domain.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class MemoryUserRepository implements UserRepository {

    private List<User> users;

    public MemoryUserRepository() {
        users = new ArrayList<>();
        users.add(new User("wldus", 123, "jiyeon", "dhso@kk.kk"));
    }
    
    @Override
    public List<User> findAll() {
        return users;
    }

    @Override
    public User findByUserId(String userId) {
        for (User user : users) {
            if (userId.equals(user.getUserId())) {
                return user;
            }
        }
        return null;
    }

    @Override
    public void save(User user) {
        users.add(user);
    }
}
