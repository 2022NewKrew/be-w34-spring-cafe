package com.kakao.cafe.user;

import java.util.ArrayList;
import java.util.List;

public class UserService {
    private Long id = 0L;
    private final List<Users> userList = new ArrayList<>();

    public List<Users> getUserList() {
        return userList;
    }

    public void createUser(Users user) {
        user.setId(id);
        id++;
        userList.add(user);
    }

    public Users getByUserId(Long id) {
        for (Users user: userList) {
            if (user.getId() == id)
                return user;
        }
        return null;
    }
}
