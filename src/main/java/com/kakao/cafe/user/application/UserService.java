package com.kakao.cafe.user.application;

import com.kakao.cafe.user.domain.User;
import com.kakao.cafe.user.dto.UserListResponse;
import com.kakao.cafe.user.dto.UserProfileResponse;
import com.kakao.cafe.user.dto.UserSaveRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@Service
public class UserService {
    private final List<UserListResponse> currentUsers = new ArrayList<>();

    public void save(UserSaveRequest request) {
        User newUser = request.toUser();
        UserListResponse userResponse = new UserListResponse(currentUsers.size() + 1, newUser);
        currentUsers.add(userResponse);
    }

    public void findAll(Map<String, Object> model) {
        model.put("users", currentUsers);
    }

    public void findById(String userId, Map<String, Object> model) throws NoSuchElementException {
        UserProfileResponse target = currentUsers.stream()
                .map(UserProfileResponse::valueOf)
                .filter(user -> user.isSameUser(userId))
                .findFirst()
                .orElseThrow(NoSuchElementException::new);

        model.put("user", target);
    }
}
