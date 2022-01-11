package com.kakao.cafe.repository.users;

import com.kakao.cafe.domain.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Repository
public class MemoryUsersRepository implements UsersRepository{

    private List<User> userList = Collections.synchronizedList(new ArrayList<>());

    @Override
    public Long save(User user) {
        user.setId(Long.valueOf(userList.size() + 1));
        userList.add(user);
        return Long.valueOf(userList.size());
    }

    @Override
    public List<User> findAll() {
        List<User> findUsers = new ArrayList<>();
        findUsers.addAll(this.userList);
        return findUsers;
    }

    @Override
    public User findByUserId(String userId) {
        return userList.stream()
                .filter(user -> userId.equals(user.getUserId()))
                .findFirst()
                .orElse(User.createEmptyUser());
    }
}
