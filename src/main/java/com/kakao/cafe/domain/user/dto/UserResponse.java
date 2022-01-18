package com.kakao.cafe.domain.user.dto;

import com.kakao.cafe.domain.user.User;
import lombok.Getter;
import lombok.Setter;

// 정보 수정할때 이름과 이메일만 내보내는 용도로.
@Setter
@Getter
public class UserResponse {
    private String userId;
    private String name;
    private String email;

    public static UserResponse from(User user) {
        UserResponse userResponse = new UserResponse();
        userResponse.setUserId(user.getUserId());
        userResponse.setEmail(user.getEmail());
        userResponse.setName(user.getName());
        return userResponse;
    }
}
