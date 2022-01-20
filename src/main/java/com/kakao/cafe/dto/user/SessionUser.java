package com.kakao.cafe.dto.user;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class SessionUser {
    int id;
    String stringId;
    String name;
    String email;
}
