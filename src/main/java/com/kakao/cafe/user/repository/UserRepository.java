package com.kakao.cafe.user.repository;

import com.kakao.cafe.user.entity.User;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class UserRepository {

    private final List<User> userList;

    private UserRepository() {
        this.userList = new ArrayList<>();
    }

    public Optional<User> findByUserId(String userId) {
        return this.userList.stream()
                            .filter(user -> user.getUserId().equals(userId))
                            .findFirst();
    }

    public Integer save(User newUser) {
        int newId = this.count() + 1;
        newUser.setId(newId);

        this.userList.add(newUser);

        return newId;
    }

    public Integer count() {
        return this.userList.size();
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
