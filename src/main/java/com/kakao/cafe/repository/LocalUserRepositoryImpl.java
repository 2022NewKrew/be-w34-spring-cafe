package com.kakao.cafe.repository;

import com.kakao.cafe.domain.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class LocalUserRepositoryImpl implements UserRepository {

    private final UserList userList;

    public LocalUserRepositoryImpl(UserList userList) {
        this.userList = userList;
    }

    @Override
    public void save(User user) {
        if (userList.isDuplicated(user)) {
            return;
        }
        userList.addUser(user);
    }

    @Override
    public List<User> findAll() {
        return userList.getUserList();
    }

    @Override
    public Optional<User> findByUserId(String userId) {
        return userList.findUserByUserId(userId);
    }
}
