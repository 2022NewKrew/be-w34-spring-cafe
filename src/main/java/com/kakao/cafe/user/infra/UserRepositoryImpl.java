package com.kakao.cafe.user.infra;

import com.kakao.cafe.user.domain.User;
import com.kakao.cafe.user.domain.UserRepository;

import java.util.ArrayList;
import java.util.List;

//@Repository
public class UserRepositoryImpl implements UserRepository {

    public static final List<User> currentUsers = new ArrayList<>();

    @Override
    public int save(User user) {
        int userIndex = currentUsers.size() + 1;
        currentUsers.add(user);
        return userIndex;
    }

    @Override
    public List<User> findAll() {
        return currentUsers;
    }

    @Override
    public User findByIdOrNull(String userId) {
        return currentUsers.stream()
                .filter(user -> user.isSameUserById(userId))
                .findFirst().orElse(null);
    }

    @Override
    public void delete(User user) {
        currentUsers.remove(user);
    }

    @Override
    public void update(User user) {

    }
}
