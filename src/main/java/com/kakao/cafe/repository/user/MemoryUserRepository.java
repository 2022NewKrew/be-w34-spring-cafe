package com.kakao.cafe.repository.user;

import com.kakao.cafe.domain.user.UserInfo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MemoryUserRepository implements UserRepository {

    private static final UserList userList = UserList.getInstance();
    @Override
    public void create(UserInfo userInfo) {
        userList.addUser(userInfo);
    }

    @Override
    public UserInfo read(String id) {
        return userList.findById(id);
    }

    @Override
    public List<UserInfo> readAll() {
        return userList.getUserList();
    }
}
