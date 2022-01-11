package com.kakao.cafe.user.repository;

import com.kakao.cafe.user.entity.User;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class UserRepository {

    private final List<User> userList;
    private Integer identity;

    private UserRepository() {
        this.userList = new ArrayList<>();
        this.identity = 0;
    }

    public Optional<User> findByUserId(String userId) {
        return this.userList.stream()
                            .filter(user -> user.getUserId().equals(userId))
                            .findFirst();
    }

    public int save(User newUser) {
        newUser.setId(++this.identity);

        this.userList.add(newUser);

        return this.identity;
    }

    public List<User> findAll() {
        return Collections.unmodifiableList(this.userList);
    }

    public Optional<User> findById(Integer id) {
        return this.userList.stream()
                       .filter(user -> Objects.equals(user.getId(), id))
                       .findFirst();
    }
}
