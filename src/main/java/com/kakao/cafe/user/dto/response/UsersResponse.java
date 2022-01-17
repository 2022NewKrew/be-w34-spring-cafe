package com.kakao.cafe.user.dto.response;

import com.kakao.cafe.user.domain.User;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class UsersResponse {
    private final List<UserResponse> userResponses;

    private UsersResponse(List<UserResponse> userResponses) {
        this.userResponses = new ArrayList<>(userResponses);
    }

    public static UsersResponse of(List<User> users) {
        return new UsersResponse(users.stream()
            .map(UserResponse::of)
            .collect(Collectors.toList()));
    }
}
