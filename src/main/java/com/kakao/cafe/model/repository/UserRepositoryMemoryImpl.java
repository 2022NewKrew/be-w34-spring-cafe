package com.kakao.cafe.model.repository;

import com.kakao.cafe.model.domain.User;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository("UserRepositoryMemoryImpl")
public class UserRepositoryMemoryImpl implements UserRepository {
    private static final Map<Long, User> storedUsers = new HashMap<>();
    private static long maxID = 0L;

    private User findUserInStoredUsers(long id) {
        return storedUsers.get(id);
    }

    private User findUserInStoredUsers(String userId) {
        return storedUsers.values().stream()
                .filter(user -> user.getUserId().equals(userId))
                .findFirst()
                .orElse(null);
    }

    private User findUserInStoredUsers(String userId, String password) {
        return storedUsers.values().stream()
                .filter(user -> user.getUserId().equals(userId) && user.getPassword().equals(password))
                .findFirst()
                .orElse(null);
    }

    @Override
    public boolean saveUser(User user) {
        if (findUserInStoredUsers(user.getUserId()) != null) {
            return false;
        }

        User newUser = User.builder()
                .id(maxID++)
                .userId(user.getUserId())
                .password(user.getPassword())
                .name(user.getName())
                .email(user.getEmail())
                .build();
        storedUsers.put(newUser.getId(), newUser);
        return true;
    }

    @Override
    public List<User> findAllUsers() {
        return new ArrayList<>(storedUsers.values());
    }

    @Override
    public Optional<User> findUserById(Long id) {
        return Optional.ofNullable(findUserInStoredUsers(id));
    }

    @Override
    public Optional<User> findUserByUserId(String userId) {
        return Optional.ofNullable(findUserInStoredUsers(userId));
    }

    @Override
    public Optional<User> findUserByLoginInfo(String userId, String password) {
        return Optional.ofNullable(findUserInStoredUsers(userId, password));
    }

    @Override
    public boolean modifyUser(User user) {
        User foundUser = findUserInStoredUsers(user.getUserId());

        if (foundUser == null) {
            return false;
        }

        User newUser = User.builder()
                .id(foundUser.getId())
                .userId(user.getUserId())
                .password(user.getPassword())
                .name(user.getName())
                .email(user.getEmail())
                .build();
        storedUsers.put(newUser.getId(), newUser);
        return true;
    }

    @Override
    public boolean deleteUser(String userId, String password) {
        User foundUser = findUserInStoredUsers(userId, password);

        if (foundUser == null) {
            return false;
        }

        storedUsers.remove(foundUser.getId());
        return true;
    }
}
