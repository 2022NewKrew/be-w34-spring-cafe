package com.kakao.cafe.repository;

import com.kakao.cafe.model.User;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class UserMemoryRepository implements UserRepository {

    private final Map<String, User> users = new HashMap<>();

    @Override
    public boolean signUp(User user) {
        if(users.containsKey(user.getUserId())){
           return false;
        }

        users.put(user.getUserId(), user);
        return true;
    }

    @Override
    public List<User> findAllUsers() {
        return null;
    }

    @Override
    public User findUserByUserId() {
        return null;
    }
}
