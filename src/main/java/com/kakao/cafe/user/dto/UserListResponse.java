package com.kakao.cafe.user.dto;

import com.kakao.cafe.user.domain.User;
import lombok.Builder;

@Builder
public class UserListResponse {

    public int index;
    public String userId;
    public String name;
    public String email;

    public static UserListResponse valueOf(int index, User user) {
        return UserListResponse.builder()
                .index(index)
                .userId(user.getUserId())
                .name(user.getName())
                .email(user.getEmail())
                .build();
    }
}
