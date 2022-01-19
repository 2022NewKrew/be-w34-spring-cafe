package com.kakao.cafe.repository.user;

import com.kakao.cafe.domain.user.Email;
import com.kakao.cafe.domain.user.Password;
import com.kakao.cafe.domain.user.User;
import com.kakao.cafe.domain.user.UserId;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryUserRepository implements UserRepository{

    private final Map<UserId, User> users = new ConcurrentHashMap<>();

    @Override
    public void save(User user) {
        users.put(user.getUserId(), user);
    }

    @Override
    public void update(UserId id, Password password, User user) {
        users.put(id, user);
    }

    @Override
    public User findById(UserId userId) {
        return users.get(userId);
    }

    @Override
    public User findByEmail(Email email) {
        return null;
    }

    @Override
    public List<User> findAll() {
        return new ArrayList<>(users.values());
    }
}
