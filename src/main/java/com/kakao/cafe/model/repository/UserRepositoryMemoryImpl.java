package com.kakao.cafe.model.repository;

import com.kakao.cafe.model.domain.User;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class UserRepositoryMemoryImpl implements UserRepository {
    private final Map<String, User> userMap = new HashMap<>();

    @Override
    public boolean insertUser(User user) {
        if (userMap.containsKey(user.getId())) {
            return false;
        }

        userMap.put(user.getId(), user);
        return false;
    }

    @Override
    public List<User> selectAllUsers() {
        List<User> userList = new ArrayList<>();

        for (Map.Entry<String, User> entry : userMap.entrySet()) {
            userList.add(entry.getValue());
        }

        return userList;
    }

    @Override
    public Optional<User> selectUserByID(String id) {
        if (!userMap.containsKey(id)) {
            return Optional.empty();
        }

        return Optional.of(userMap.get(id));
    }

    @Override
    public Optional<User> selectUserByLoginInfo(String id, String password) {
        if (!userMap.containsKey(id) || !userMap.get(id).getPassword().equals(password)) {
            return Optional.empty();
        }

        return Optional.of(userMap.get(id));
    }

    @Override
    public boolean updateUser(User user) {
        if (!userMap.containsKey(user.getId())) {
            return false;
        }

        userMap.put(user.getId(), user);
        return true;
    }

    @Override
    public boolean deleteUser(String id, String password) {
        if (!userMap.containsKey(id) || !userMap.get(id).getPassword().equals(password)) {
            return false;
        }

        userMap.remove(id);
        return true;
    }
}
