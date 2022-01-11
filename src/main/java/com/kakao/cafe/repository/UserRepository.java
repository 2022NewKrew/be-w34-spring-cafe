package com.kakao.cafe.repository;

import domain.UserList;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepository {
    private final UserList userList;

    public UserRepository() {
        userList = new UserList();
    }

    public UserList getUserList() {
        return userList;
    }
}
