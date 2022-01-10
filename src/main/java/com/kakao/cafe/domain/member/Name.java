package com.kakao.cafe.domain.member;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class Name {

    private final String name;

    @Override
    public String toString() {
        return name;
    }
}
