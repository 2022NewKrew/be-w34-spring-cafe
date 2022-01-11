package com.kakao.cafe.domain.user;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Users {
    private final List<User> users;

    public Users() {
        users = new ArrayList<>();
    }

    public Users(List<User> users) {
        this.users = users;
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

    public boolean update(String id, String oldPassword, User newInformation) {
        User target = getByUserId(id);
        if (target == null) return false;
        if (!target.passwordIs(oldPassword)) return false;
        int targetIndex = users.indexOf(target);
        users.remove(target);
        users.add(targetIndex, newInformation);
        return true;
    }

    public int size() {
        return users.size();
    }
}
