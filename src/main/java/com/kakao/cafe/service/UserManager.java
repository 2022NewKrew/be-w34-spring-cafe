package com.kakao.cafe.service;

import com.kakao.cafe.domain.User;
import com.kakao.cafe.domain.Users;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class UserManager implements UserService {
    private final Users users = new Users();

    @Override
    public void add(@NonNull final User user) {
        if (users.checkIdExist(Objects.requireNonNull(user))) {
            throw new IllegalStateException("Duplicate id is exist!");
        }
        users.add(user);
    }

    @Override
    public List<User> getList() {
        return users.getList();
    }
}
