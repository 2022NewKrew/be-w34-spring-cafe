package com.kakao.cafe.dto.user;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class SessionUser {
    Long id;
    String userId;
}
