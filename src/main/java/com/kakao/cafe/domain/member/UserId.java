package com.kakao.cafe.domain.member;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class UserId {

    private final String userId;

    @Override
    public String toString() {
        return userId;
    }

    @Override
    public boolean equals(Object o) {
        if (getClass() != o.getClass())
            return false;
        UserId id = (UserId) o;
        return id.getUserId() != null && id.getUserId().equals(userId);
    }
}
