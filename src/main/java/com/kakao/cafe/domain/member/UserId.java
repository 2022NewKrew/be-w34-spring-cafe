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
}
