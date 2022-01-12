package com.kakao.cafe.db;

import com.kakao.cafe.domain.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JakeDB {
    private final List<User> userList = new ArrayList<>();

    public List<User> getUserList() {
        return userList;
    }
}
