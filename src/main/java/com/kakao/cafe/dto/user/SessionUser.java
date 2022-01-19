package com.kakao.cafe.dto.user;

import lombok.Builder;

@Builder
public class SessionUser {
    int id;
    String stringId;
    String name;
    String email;
}
