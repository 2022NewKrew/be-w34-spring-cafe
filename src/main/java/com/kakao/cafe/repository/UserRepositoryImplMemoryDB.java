package com.kakao.cafe.repository;

import com.kakao.cafe.domain.User;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepositoryImplMemoryDB implements UserRepository {

    private final Map<String, User> userMemoryDB = new HashMap<>();

    @Override
    public void createUser(User targetUser) {
        userMemoryDB.put(targetUser.getUserId(), targetUser);
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
}
