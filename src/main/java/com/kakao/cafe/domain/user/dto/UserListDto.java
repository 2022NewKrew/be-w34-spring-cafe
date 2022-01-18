package com.kakao.cafe.domain.user.dto;

import com.kakao.cafe.domain.user.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserListDto {
    private int index;
    private String userId;
    private String name;
    private String email;

    public static UserListDto from(User from, int idx) {
        UserListDto user = new UserListDto();
        user.setUserId(from.getUserId());
        user.setEmail(from.getEmail());
        user.setName(from.getName());
        user.setIndex(idx);
        return user;
    }
}
