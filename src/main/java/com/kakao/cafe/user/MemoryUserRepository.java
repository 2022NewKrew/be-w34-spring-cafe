package com.kakao.cafe.user;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by melodist
 * Date: 2022-01-10 010
 * Time: 오후 1:38
 */
@Repository
public class MemoryUserRepository implements UserRepository{
    private static List<User> users = new ArrayList<>();

    @Override
    public User save(User user) {
        users.add(user);
        return user;
    }

    @Override
    public List<User> findAll() {
        return users;
    }

    @Override
    public User findUserByUserId(String userId) {
        for (User user: users) {
            if (user.getUserId().equals(userId)) {
                return user;
            }
        }
        return null;
    }
}
