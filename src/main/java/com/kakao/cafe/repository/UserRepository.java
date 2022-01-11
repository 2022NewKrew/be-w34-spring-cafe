package com.kakao.cafe.repository;

import domain.UserList;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {
    UserList userList = UserList.getInstance();
    public UserRepository() {

    }

    public UserList getUserList() {
        return userList;
    }
}
