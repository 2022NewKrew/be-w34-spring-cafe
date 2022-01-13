package com.kakao.cafe.controller.dto;

import com.kakao.cafe.domain.User;
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
