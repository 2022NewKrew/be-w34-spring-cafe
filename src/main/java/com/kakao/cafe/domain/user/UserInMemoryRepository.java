package com.kakao.cafe.domain.user;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public class UserInMemoryRepository implements UserRepository {
    private static final ArrayList<User> userList = new ArrayList<>();

    @Override
    public void save(User user) {
        userList.add(user);
    }
}
