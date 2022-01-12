package com.kakao.cafe.user.dto.response;

import com.kakao.cafe.user.domain.User;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class UsersResponse {
    private final List<UserResponse> users;

    // Q. 저번에 알려주신 unmodifiableList를 사용하면 좋을까요?
    public UsersResponse(List<UserResponse> users) {
        this.users = new ArrayList<>(users);
    }

    public static UsersResponse of(List<User> users) {
        return new UsersResponse(users.stream()
            .map(UserResponse::of)
            .collect(Collectors.toList()));
    }

    public List<UserResponse> getUsers() {
        return users;
    }
}
