package com.kakao.cafe.service;

import com.kakao.cafe.model.User;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserServiceImpl implements UserService {

    @Override
    public User filterUserById(List<User> users, String userId) {
        return users.stream()
                .filter(user -> user.getUserId().equals(userId))
                .findFirst()
                .orElse(null);
    }

}
