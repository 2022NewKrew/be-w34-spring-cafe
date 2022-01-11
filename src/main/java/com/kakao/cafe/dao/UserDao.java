package com.kakao.cafe.dao;

import com.kakao.cafe.domain.User;
import org.springframework.stereotype.Repository;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public class UserDao {
    private static UserDao instance = null;

    private long userId;
    private List<User> users;

    private UserDao() {
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
        final User newUser = new User(user);
        newUser.setUserId(userId++);
        newUser.setCreateDate(getCurrentTime());

        users.add(newUser);
    }

    public List<User> getUsers() {
        return users;
    }

    public boolean checkDuplicateUser(User newUser) {
        final int userNicknameCount = (int) users.stream()
                .filter(user -> user.getNickname().equals(newUser.getNickname()) ||
                        user.getEmail().equals(newUser.getEmail()))
                .count();

        if (userNicknameCount > 0) {
            return true;
        }

        return false;
    }
    
    public User getUser(long userId) {
        return users.stream()
                .filter(user -> user.getUserId() == userId)
                .findFirst().orElse(null);
    }

    private String getCurrentTime() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return simpleDateFormat.format(new Date());
    }
}
