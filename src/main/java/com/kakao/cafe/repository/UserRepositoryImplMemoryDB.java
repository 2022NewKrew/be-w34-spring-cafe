package com.kakao.cafe.repository;

import com.kakao.cafe.domain.User;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepositoryImplMemoryDB implements UserRepository {

    private final Map<String, User> userMemoryDB = new ConcurrentHashMap<>();

    @Override
    public void createUser(User user) {
        userMemoryDB.put(user.getUserId(), user);
    }

    @Override
    public boolean isUserIdUsed(String userId) {
        return userMemoryDB.containsKey(userId);
    }

    @Override
    public Collection<User> readUserList() {
        return userMemoryDB.values();
    }

    @Override
    public User readByUserId(String userId) {
        return userMemoryDB.get(userId);
    }

    @Override
    public User updateUser(User user) {
        return user;
    }
}
