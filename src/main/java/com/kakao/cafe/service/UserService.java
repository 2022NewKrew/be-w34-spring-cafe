package com.kakao.cafe.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UserService {

    private final List<User> userList = new ArrayList<>();

    public void insertUser(User user) {
        userList.add(user);
    }

    public List<User> getUserList() {
        return userList;
    }

    public User getUserById(String id) {
        User findUser = userList.stream().filter(user -> Objects.equals(user.getUserId(), id)).findAny()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "user not found"));

        return findUser;
    }
}
