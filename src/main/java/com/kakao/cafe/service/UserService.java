package com.kakao.cafe.service;

import com.kakao.cafe.domain.User;
import com.kakao.cafe.dto.UserProfileResponse;
import com.kakao.cafe.dto.UserSignUpRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    public void signUp(UserSignUpRequest request, List<User> users) {
        User user = new User(User.cnt++, request.getUserId(), request.getPw(), request.getName(), request.getEmail());
        users.add(user);
    }

    public UserProfileResponse profile(int userId, List<User> users) {
        User user = users.get(userId - 1);
        return new UserProfileResponse(user.getId(), user.getUserId(), user.getName(), user.getEmail());
    }
}
