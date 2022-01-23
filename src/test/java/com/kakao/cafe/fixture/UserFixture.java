package com.kakao.cafe.fixture;

import com.kakao.cafe.user.domain.User;
import com.kakao.cafe.user.dto.SessionUser;
import java.time.LocalDateTime;

public class UserFixture {

    public static final SessionUser LOGIN_USER = new SessionUser(1L, "tester1");

    public static final User USER1 = User.builder()
        .id(1L)
        .email("tester1@test.com")
        .password("password")
        .nickname("tester1")
        .createdAt(LocalDateTime.now())
        .build();

    public static final User USER2 = User.builder()
        .id(2L)
        .email("tester2@test.com")
        .password("password")
        .nickname("tester2")
        .createdAt(LocalDateTime.now())
        .build();
}