package com.kakao.cafe.user.domain;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Repository
public class UserRepository {
    private final List<User> userLst;

    public UserRepository() {
        userLst = new ArrayList<>();
    }

    public boolean signUp(String userId, String password, String name, String email) {
        for (User existUser : userLst) {
            if (existUser.getUserId().equals(userId)) {
                return false;
            }
        }

        userLst.add(new User(userId, password, name, email));
        return true;
    }

    public User getUser(String userId) {
        for (User user : userLst) {
            if (user.getUserId().equals(userId)) {
                return user;
            }
        }

        return null;
    }

    public List<User> getUserLst() {
        return Collections.unmodifiableList(userLst);
    }
}
