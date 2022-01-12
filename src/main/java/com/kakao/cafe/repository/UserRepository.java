package com.kakao.cafe.repository;

import com.kakao.cafe.domain.User;
import com.kakao.cafe.exceptions.InvalidUser;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

@Repository
public class UserRepository {

    private final List<User> userList;

    public UserRepository() {
        this.userList = Collections.synchronizedList(new ArrayList<>());
    }

    public void save(User user) {
        userList.add(user);
    }

    public List<User> findAll() {
        return userList;
    }

    public User findByUserId(String id) {
        User findUser = userList.stream().filter(user -> Objects.equals(user.getUserId(), id)).findAny()
                .orElseThrow(() -> new InvalidUser("사용자 ID가 없습니다"));

        return findUser;
    }
}
