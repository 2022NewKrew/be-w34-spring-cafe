package com.kakao.cafe.service;

import com.kakao.cafe.domain.User;
import com.kakao.cafe.domain.Users;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class UserManager implements UserService {
    private final Users users = new Users();

    @Override
    public boolean add(@NonNull final User user) {
        users.add(Objects.requireNonNull(user));
        return false;
    }
}
