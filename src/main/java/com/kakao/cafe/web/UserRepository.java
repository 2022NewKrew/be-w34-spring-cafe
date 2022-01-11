package com.kakao.cafe.web;

import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.NoSuchElementException;

@Repository
public class UserRepository {
    private final ArrayList<User> userList;

    public UserRepository() {
        userList = new ArrayList<>();
    }

    public void addUser(User user) throws IllegalArgumentException {
        for(User u: userList) {
            if(u.getUserId().equals(user.getUserId())) {
                throw new IllegalArgumentException("아이디 중복");
            }
        }
        userList.add(user);
    }

    public ArrayList<User> getUserList() {
        return userList;
    }

    public User findUserWithId(String id) throws NoSuchElementException {
        for(User user: userList) {
            if(user.getUserId().equals(id)) return user;
        }
        throw new NoSuchElementException("아이디 없음");
    }
}
