package com.kakao.cafe.dao;

import com.kakao.cafe.domain.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserDao {
    private static UserDao instance = null;

    private long userId;
    private List<User> users;

    public UserDao() {
        userId = 0l;
        users = new ArrayList<>();
    }

    public static UserDao getInstance() {
        if (instance == null) {
            instance = new UserDao();
        }

        return instance;
    }

    public void createUser(User user) {
        final User newUser = User.from(user);
        newUser.setUserId(userId++);

        users.add(newUser);
    }

    public List<User> getUsers() {
        return users;
    }

    public boolean checkDuplicateUser(User newUser) {
        final boolean isDuplicateUser = users.stream()
                .anyMatch(user -> user.getNickname().equals(newUser.getNickname()) ||
                        user.getEmail().equals(newUser.getEmail()));

        return isDuplicateUser;
    }
    
    public User getUser(long userId) {
        return users.stream()
                .filter(user -> user.getUserId() == userId)
                .findFirst()
                .orElse(null);
    }

    public User loginUser(String email, String password) {
        return users.stream()
                .filter(user -> user.getEmail().equals(email) &&
                        user.getPassword().equals(password))
                .findFirst()
                .orElse(null);
    }
}
