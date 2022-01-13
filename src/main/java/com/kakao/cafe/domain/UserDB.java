package com.kakao.cafe.domain;

import java.util.ArrayList;
import java.util.List;

public class UserDB {
    private static final List<User> userLst = new ArrayList<>();

    public UserDB() {
        SignUp("skian", "1234", "faust", "faust.like@kakaocorp.com");
        SignUp("dbwhdgus", "1234", "yjh", "dbwhdgus12@naver.com");
    }

    public boolean SignUp(String userId, String password, String name, String email) {
        for (User existUser : userLst) {
            if (existUser.getuserId().equals(userId)) {
                return false;
            }
        }

        userLst.add(new User(userId, password, name, email));
        return true;
    }

    public User getUser(String userId) {
        for (User user : userLst) {
            if (user.getuserId().equals(userId)) {
                return user;
            }
        }

        return null;
    }

    public List<User> getUserLst() {
        return List.copyOf(userLst);
    }
}
