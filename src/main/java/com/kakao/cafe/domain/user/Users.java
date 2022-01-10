package com.kakao.cafe.domain.user;

import com.kakao.cafe.domain.post.Writer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Users {
    private final List<User> users;

    public Users() {
        users = new ArrayList<>();
    }

    public Users(User... users) {
        this.users = new ArrayList<>(Arrays.asList(users));
    }

    public void add(User user) {
        this.users.add(user);
    }

    public User getByUserId(String userId) {
        for (User user : users)
            if (user.isUserId(userId)) return user;
        return null;
    }

    public List<User> getUsers() {
        return users;
    }

    public Writer writer(String userId){
        User target = getByUserId(userId);
        if(target == null)
            throw new IllegalArgumentException("없는 사용자입니다!");
        return new Writer(target);
    }
}
