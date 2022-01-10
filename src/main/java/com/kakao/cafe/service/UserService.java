package com.kakao.cafe.service;

import com.kakao.cafe.domain.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    List<User> users = new ArrayList<>();

    public void signUp(User user) {
        users.add(user);
    }

    public List<User> getUsers() {
        return users;
    }
}
