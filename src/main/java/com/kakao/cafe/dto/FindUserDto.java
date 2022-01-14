package com.kakao.cafe.dto;

import com.kakao.cafe.domain.UserId;

public class FindUserDto {

    private final UserId userId;

    public FindUserDto(UserId userId) {
        this.userId = userId;
    }

    public UserId getUserId() {
        return userId;
    }
}
