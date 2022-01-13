package com.kakao.cafe.web.dto.user;

import com.kakao.cafe.domain.user.User;
import lombok.Getter;

import java.util.List;

@Getter
public class UsersListResponseDto {
    private List<User> users;

    public UsersListResponseDto(List<User> users) {
        this.users = users;
    }
}
