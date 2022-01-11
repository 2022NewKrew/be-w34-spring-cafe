package com.kakao.cafe.service;

import com.kakao.cafe.domain.User;
import com.kakao.cafe.dto.UserSignUpRequest;
import com.kakao.cafe.dto.UserUpdateRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    private static final List<User> users = new ArrayList<>();

    public void signUp(UserSignUpRequest request) {
        User user = new User(User.cnt++, request.getUserId(), request.getPassword(), request.getName(), request.getEmail());
        users.add(user);
    }

    public User profile(int id) {
        return users.get(id - 1);
    }

    public List<User> list() {
        return users;
    }

    public boolean updateUser(int id, UserUpdateRequest request) {
        User user = users.get(id - 1);
        if (!user.getPassword().equals(request.getPassword())) {
            return false;
        }

        user.setName(request.getName());
        user.setPassword(request.getNewPassword());
        user.setEmail(request.getEmail());
        return true;
    }
}
