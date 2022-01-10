package com.kakao.cafe.repository;

import com.kakao.cafe.model.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepository {

    private final List<User> data = new ArrayList<>();

    public User create(User user) {
        data.add(user);
        return user;
    }
}
