package com.kakao.cafe.domain.member;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class Email {

    private final String email;

    @Override
    public String toString() {
        return email;
    }
}
